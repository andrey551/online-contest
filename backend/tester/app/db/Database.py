import motor.motor_asyncio

from app.core.Settings import settings

client = motor.motor_asyncio.AsyncIOMotorClient(settings.ENGINE)

database = client.tad

test_collection = database.get_collection("test_set_db")
