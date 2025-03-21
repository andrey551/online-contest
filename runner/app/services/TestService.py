from bson import ObjectId
from uuid import UUID

from runner.app.db.Database import test_collection
from runner.app.models.TestSet import TestSet
from runner.app.schemas.responses.ResponseModel import BaseResponse, ResponseSuccess, ResponseError


def import_tests(tests: TestSet) -> BaseResponse:
    tests_dict = tests.to_dict()

    tests = test_collection.insert_one(tests_dict)

    if tests.inserted_id:
        return ResponseSuccess(status_code = 200, data = tests_dict)
    else:
        return ResponseError(status_code = 400, message = "Can not insert test")

def retrieve_tests(test_id: UUID) -> BaseResponse:
    test = test_collection.find_one({"_id": test_id})

    if test is None:
        return ResponseError(status_code = 404, message = "Can not find test")

    return ResponseSuccess(status_code = 200, data = test)

def delete_tests(test_id: UUID) -> BaseResponse:
    test = test_collection.find_one({"_id": test_id})

    if test is None:
        return ResponseError(status_code = 404, message = "Can not find test")
    test_collection.delete_one({"_id": test_id})

    return ResponseSuccess(status_code = 200, data = None)


