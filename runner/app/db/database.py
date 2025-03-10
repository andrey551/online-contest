import motor.motor_asyncio

MONGO_DETAILS = "mongodb://localhost:27017"

client = motor.motor_asyncio.AsyncIOMotorClient(MONGO_DETAILS)

database = client.testset

testset_collection = database.get_collection("testset")

def testset_helper(testset) -> dict:
    return {
        "id": str(testset["_id"]),
        "problem_id": str(testset["problem_id"]),
        "problem": testset["problem_name"],
        "testcases": testset["testcases"],
    }
