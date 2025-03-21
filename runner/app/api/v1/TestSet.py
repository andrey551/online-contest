from uuid import UUID

from Base import app
from runner.app.models.TestSet import TestSet
from runner.app.services.TestService import import_tests, delete_tests

""""
TestSet API: Control test set for every laboratory
"""
@app.post("/api/v1/test-set")
def insert_tests(tests: TestSet):
        response = import_tests(tests)
        return response

@app.get("/api/v1/test-set/{uuid}")
def retrieve_tests(uuid: UUID):
    response = retrieve_tests(uuid)
    return response

@app.delete("/api/v1/test-set/{uuid}")
def delete_tests(uuid: UUID):
    response = delete_tests(uuid)
    return response



