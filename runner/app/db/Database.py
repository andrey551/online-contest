import motor.motor_asyncio

from runner.app.core.Settings import settings

client = motor.motor_asyncio.AsyncIOMotorClient(settings.MONGO_URI)

database = client.tad

resource_collection = database.get_collection("resource")

test_collection = database.get_collection("test_set_db")
