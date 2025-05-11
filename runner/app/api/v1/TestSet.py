import logging
from uuid import UUID

from app.models.TestSet import TestSet
from app.services.test.TestService import import_tests, delete_tests
from fastapi import APIRouter

test_set_router = APIRouter()
logger = logging.getLogger(__name__)
""""
TestSet API: Control test set for every laboratory
"""


@test_set_router.post("/api/v1/test-set")
async def insert_tests(tests: TestSet):
    logger.info(f"Inserting test: {tests}")
    response = await import_tests(tests)
    return response


@test_set_router.get("/api/v1/test-set/{uuid}")
def retrieve_tests(uuid: UUID):
    response = retrieve_tests(uuid)
    return response


@test_set_router.delete("/api/v1/test-set/{uuid}")
def delete_tests(uuid: UUID):
    response = delete_tests(uuid)
    return response
