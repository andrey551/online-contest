import json
import uuid
from argparse import Namespace
from http.client import HTTPException, HTTPResponse

from uuid import UUID

import logging
from bson import Binary, ObjectId

from runner.app.db.Database import resource_collection
from runner.app.models.Resource import Resource
from runner.app.services.DockerManager import DockerManager
from runner.app.services.Runner import Runner

logger = logging.getLogger(__name__)

async def insert_resource(resource: Resource):
    resource_dict = resource.dict()
    resource_dict["problem_id"] = str(resource_dict["problem_id"])
    result = await resource_collection.insert_one(resource_dict)
    if result.inserted_id:
        resource_dict["_id"] = str(result.inserted_id)
        return resource_dict
    else:
        raise HTTPException(status_code=400, detail="Failed to insert item")

def update_resource(id: UUID, resource: Resource):
    resource_dict = resource.to_dict()
    resource = resource_collection.update_one({"_id": id}, {"$set": resource_dict})
    if resource.matched_count:
        return HTTPResponse(status_code=200)
    else:
        raise HTTPException(status_code=400, detail="Failed to update item")

def delete_resource(id: UUID):
    resource = resource_collection.delete_one({"_id": id})
    if resource.matched_count:
        return HTTPResponse(status_code=204)
    else:
        raise HTTPException(status_code=400, detail="Failed to delete item")

def get_resource(id: UUID):
    resource = resource_collection.find_one({"_id": id})
    if resource.matched_count:
        return HTTPResponse(status_code=200, content=resource.to_dict())
    else:
        raise HTTPException(status_code=400, detail="Failed to get item")

async def get_resource_by_problem_id(problem_id: str):
    resource = await resource_collection.find_one({"problem_id": problem_id})
    logger.info(resource)
    if resource["problem_id"] is not None:
        return resource
    return None

""""
pull docker image if didn't exist
"""
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
            raise HTTPException(status_code=400, detail="Failed to get item")
    except Exception as e:
        logger.error(e)
        raise HTTPException(status_code=400, detail="Failed to get item")


