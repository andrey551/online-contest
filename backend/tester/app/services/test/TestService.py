from http.client import HTTPException

from uuid import UUID

from app.db.Database import test_collection
from app.models.TestSet import TestSet
from app.schemas.responses.ResponseModel import BaseResponse


async def import_tests(tests: TestSet):
    tests_dict = tests.dict()
    tests_dict["laboratory_id"] = str(tests_dict["laboratory_id"])

    tests_inserted = await test_collection.insert_one(tests_dict)

    if tests_inserted.inserted_id:
        tests_dict["_id"] = str(tests_inserted.inserted_id)
        return tests_dict
    else:
        raise HTTPException(status_code=400, detail="Failed to insert item")


async def retrieve_tests(test_id: UUID) -> BaseResponse:
    test = await test_collection.find_one({"_id": test_id})

    if test is None:
        return BaseResponse(status_code=404,
                            headers=None,
                            body="Can not find test")

    return BaseResponse(status_code=200, headers=None, body=test)


def delete_tests(test_id: UUID) -> BaseResponse:
    test = test_collection.find_one({"_id": test_id})

    if test is None:
        return BaseResponse(status_code=404, headers=None, body="Can not find test")
    test_collection.delete_one({"_id": test_id})

    return BaseResponse(status_code=200, headers=None, body=None)


async def retrieve_test_by_problem_id(laboratory_id: str) -> TestSet | None:
    return await test_collection.find_one({"laboratory_id": laboratory_id})
