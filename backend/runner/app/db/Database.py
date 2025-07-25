import motor.motor_asyncio

from app.core.Settings import settings

client = motor.motor_asyncio.AsyncIOMotorClient(settings.ENGINE,
                                                maxPoolSize=1000,
                                                minPoolSize=10,
                                                connectTimeoutMS=30000,
                                                socketTimeoutMS=30000
                                                )

database = client.tad

resource_collection = database.get_collection("resource")

test_collection = database.get_collection("test_set_db")

solution_collection = database.get_collection("solution")
