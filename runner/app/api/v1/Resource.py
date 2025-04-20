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
def update_resources(id: UUID, resource: Resource):
    try:
        response = update_resource(id, resource)
        return response
    except Exception as e:
        return e

@resource_router.delete('/api/v1/resources/{id}')
def delete_resources():
    try:
        response = delete_resource(id)
        return response
    except Exception as e:
        return e

@resource_router.get('/api/v1/resources/{id}')
def get_resources():
    try:
        response = get_resource(id)
        return response
    except Exception as e:
        return e

@resource_router.get('/api/v1/resources/run/{id}')
async def implement_resources(id: str):
    try:
        response = await impl_resource(id)
        return response
    except Exception as e:
        return e

