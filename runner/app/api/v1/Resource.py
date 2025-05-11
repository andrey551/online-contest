from app.services.resource.ResourceService import *
from fastapi import APIRouter

resource_router = APIRouter()
logger = logging.getLogger(__name__)

""""
Resource API: To control resources of docker (Docker image, docker container)
"""


@resource_router.post('/api/v1/resources')
async def require_resources(resource: Resource):
    logger.info("Adding resource")
    try:
        response = await insert_resource(resource)
        logger.info(response)
        return response
    except Exception as e:
        logger.error(e)
        return e

@resource_router.put('/api/v1/resources')
async def update_resources(id: UUID , resource: Resource):
    try:
        response = await update_resource(id, resource)
        return response
    except Exception as e:
        return e

@resource_router.delete('/api/v1/resources/{id}')
async def delete_resources():
    try:
        response = await delete_resource(id)
        return response
    except Exception as e:
        return e

@resource_router.get('/api/v1/resources/{id}')
async def get_resources(id: str):
    try:
        response = await get_resource_by_problem_id(id)
        logger.info(response)
        return response.to_dict()
    except Exception as e:
        return e

@resource_router.get('/api/v1/resources/run/{id}')
async def implement_resources(id: str):
    try:
        response = await impl_resource(id)
        return response
    except Exception as e:
        return e

