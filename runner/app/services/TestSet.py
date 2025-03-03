from typing import Any, Coroutine
from uuid import UUID

from runner.app.db.database import testset_collection, testset_helper
from runner.app.models.Problem import Problem

from bson.objectid import ObjectId

async def create_problem(data: Problem):
    testset = await testset_collection.insert_one(data)
    new_testset = await testset_collection.find_one({"_id": ObjectId(testset.inserted_id)})
    return testset_helper(new_testset)

async def update_problem(id: UUID, data: Problem):
    if(len(data) == 0):
        return False
    testset = await testset_collection.find_one({"_id": ObjectId(id)})
    if testset:
        updated_testset = await testset_collection.update_one({"_id": ObjectId(id)}, {"$set": data})
        if updated_testset:
            return True

    return False

async def delete_problem(id: UUID):
    testset = await testset_collection.find_one({"_id": ObjectId(id)})
    if testset:
        await testset_collection.delete_one({"_id": ObjectId(id)})
        return True

async def retrieve_testset(id: UUID) -> dict | bool:
    testset = await testset_collection.find_one({"_id": ObjectId(id)})

    if testset:
        return testset_helper(testset)

    return False

