import logging
from uuid import UUID

from fastapi import APIRouter, Form

from app.models.TestSet import TestSet
from app.schemas.requests.RequestModel import RunTestRequestModel
from app.services.test.TestManager import TestManager
from app.services.test.TestService import import_tests, delete_tests
from app.services.test.gRPCService import get_container_id_by_laboratory_id

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

@test_set_router.post("/api/v1/test-set/run")
async def run_tests(request: RunTestRequestModel):
    tests = TestManager()
    response = await tests.get_report(request)
    return response

@test_set_router.get("/api/v1/test-set/test/{uuid}")
async def test(uuid: str):
    response = await get_container_id_by_laboratory_id(uuid)
    return response



