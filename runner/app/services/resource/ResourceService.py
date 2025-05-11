import logging
from http.client import HTTPException, HTTPResponse
from uuid import UUID

from app.db.Database import resource_collection
from app.models.Resource import Resource
from app.services.docker.DockerManager import DockerManager
from bson import ObjectId

logger = logging.getLogger(__name__)


async def insert_resource(resource: Resource):
    resource_dict = resource.dict()
    logger.info(resource_dict)
    resource_dict["laboratory_id"] = str(resource_dict["laboratory_id"])
    result = await resource_collection.insert_one(resource_dict)
    if result.inserted_id:
        resource_dict["_id"] = str(result.inserted_id)
        return resource_dict
    else:
        raise HTTPException(status_code=400, detail="Failed to insert item")


async def update_resource(id: UUID, resource: Resource):
    resource_dict = resource.to_dict()
    resource = await resource_collection.update_one({"_id": id}, {"$set": resource_dict})
    if resource.matched_count:
        return HTTPResponse(status_code=200)
    else:
        raise HTTPException(status_code=400, detail="Failed to update item")


async def delete_resource(id: UUID):
    resource = await resource_collection.delete_one({"_id": id})
    if resource.matched_count:
        return HTTPResponse(status_code=204)
    else:
        raise HTTPException(status_code=400, detail="Failed to delete item")


async def get_resource(id: UUID):
    resource = await resource_collection.find_one({"_id": id})
    if resource.matched_count:
        return HTTPResponse(status_code=200, content=resource.to_dict())
    else:
        raise HTTPException(status_code=400, detail="Failed to get item")


async def get_resource_by_laboratory_id(laboratory_id: str):
    resource = await resource_collection.find_one({"laboratory_id": laboratory_id})
    logger.info(resource)
    if resource:
        return resource
    return None


async def impl_resource(id: str) -> object:
    logger.info("getting ID")
    try:
        resource = await resource_collection.find_one({"_id": ObjectId(id)})
        logger.info(resource)

        if resource:
            docker_container = DockerManager()
            try:
                for container in resource["list_images"]:
                        await docker_container.add_image(container["image_name"])
                return {"status": "success"}
            except Exception as e:
                logger.info(e)
                return {"status": "error"}

        else:
            logger.error(f"{id} not found")
            raise HTTPException("Failed to get item")
    except Exception as e:
        logger.error(e)
        raise HTTPException("Failed to get item")
