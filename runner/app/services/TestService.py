from bson import ObjectId
from uuid import UUID

from runner.app.db.Database import test_collection
from runner.app.models.TestSet import TestSet
from runner.app.schemas.responses.ResponseModel import BaseResponse


def import_tests(tests: TestSet) -> BaseResponse:
    tests_dict = tests.to_dict()

    tests = test_collection.insert_one(tests_dict)

    if tests.inserted_id:
        return BaseResponse(status_code = 200, headers=None,  body = tests_dict)
    else:
        return BaseResponse(status_code = 400, headers=None, body = "Can not insert test")

def retrieve_tests(test_id: UUID) -> BaseResponse:
    test = test_collection.find_one({"_id": test_id})

    if test is None:
        return BaseResponse(status_code = 404, headers=None,  body = "Can not find test")

    return BaseResponse(status_code = 200, headers=None,  body = test)

def delete_tests(test_id: UUID) -> BaseResponse:
    test = test_collection.find_one({"_id": test_id})

    if test is None:
        return BaseResponse(status_code = 404, headers=None, body = "Can not find test")
    test_collection.delete_one({"_id": test_id})

    return BaseResponse(status_code = 200, headers=None, body = None)


