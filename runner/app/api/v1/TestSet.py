from uuid import UUID

from fastapi import APIRouter

from runner.app.models.TestSet import TestSet
from runner.app.services.TestService import import_tests, delete_tests

test_set_router = APIRouter()
""""
TestSet API: Control test set for every laboratory
"""
@test_set_router.post("/api/v1/test-set")
def insert_tests(tests: TestSet):
        response = import_tests(tests)
        return response

@test_set_router.get("/api/v1/test-set/{uuid}")
def retrieve_tests(uuid: UUID):
    response = retrieve_tests(uuid)
    return response

@test_set_router.delete("/api/v1/test-set/{uuid}")
def delete_tests(uuid: UUID):
    response = delete_tests(uuid)
    return response



