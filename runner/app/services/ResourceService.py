from http.client import HTTPException, HTTPResponse

from uuid import UUID

from runner.app.db.Database import resource_collection
from runner.app.models.Resource import Resource
from runner.app.services.DockerManager import DockerManager


def insert_resource(resource: Resource):
    resource_dict = resource.to_dict()

    resource = resource_collection.insert_one(resource_dict)

    if resource.inserted_id:
        return HTTPResponse(status_code=200, content=resource_dict)
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

""""
pull docker image if didn't exist
"""
def impl_resource(id: UUID, doker_container : DockerManager):
    resource = resource_collection.find_one({"_id": id})

    if resource.matched_count:
        try:
            for container in resource.list_images:
                doker_container.add_image(container.image_name)
        except Exception as e:
            raise e
    else:
        raise HTTPException(status_code=400, detail="Failed to get item")

