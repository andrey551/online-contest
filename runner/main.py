import asyncio
import logging

from fastapi import FastAPI
from contextlib import asynccontextmanager
from app.api.v1.Base import base_router
from app.api.v1.Resource import resource_router
from app.api.v1.Solution import solution_router
from app.api.v1.TestSet import test_set_router
from app.services.grpc.GrpcService import serve  # Make sure this is correct

# Configure logging
logging.basicConfig(
    level=logging.INFO,  # Set the logging level (DEBUG, INFO, WARNING, ERROR, CRITICAL)
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",  # Log format
    handlers=[
        logging.FileHandler("runner.log"),  # Log to a file
        logging.StreamHandler()  # Log to the console
    ]
)

# Lifespan context manager for managing FastAPI startup and shutdown
@asynccontextmanager
async def lifespan(app: FastAPI):
    # Start the gRPC server in background
    print("🚀 FastAPI is starting and the gRPC server will run in the background.")
    grpc_task = asyncio.create_task(serve())  # Run gRPC server in background

    yield  # Control passes to FastAPI here, allowing it to run while gRPC server is active

    # Shutdown logic: Cancel the gRPC task when FastAPI shuts down
    print("🛑 FastAPI is shutting down...")
    grpc_task.cancel()  # Gracefully cancel the gRPC server task
    try:
        await grpc_task  # Wait for the task to finish
    except asyncio.CancelledError:
        print("gRPC server has been cancelled.")

# Initialize FastAPI with the lifespan context
app = FastAPI(lifespan=lifespan)

# Include routers for API endpoints
app.include_router(base_router)
app.include_router(resource_router)
app.include_router(solution_router)
app.include_router(test_set_router)