from Base import app
from runner.app.models.Resource import Resource
from runner.app.services.ResourceService import insert_resource

""""
Resource API: To control resources of docker (Docker image, docker container)
"""


@app.post('/api/v1/resources')
def require_resources(resource: Resource):
    try:
        response = insert_resource(resource)
        return response
    except Exception as e:
        return e

@app.put('/api/v1/resources')
def update_resource(resource: Resource):
    try:
        response = update_resource(resource)
        return response
    except Exception as e:
        return e

@app.delete('/api/v1/resources/{id}')
def delete_resource():
    try:
        response = delete_resource(id)
        return response
    except Exception as e:
        return e

@app.get('/api/v1/resources/{id}')
def get_resource():
    try:
        response = get_resource(id)
        return response
    except Exception as e:
        return e

