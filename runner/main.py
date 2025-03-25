
import logging

from fastapi import FastAPI

from runner.app.api.v1.Base import base_router
from runner.app.api.v1.Resource import resource_router
from runner.app.api.v1.Solution import solution_router
from runner.app.api.v1.TestSet import test_set_router

logging.basicConfig(
    level=logging.INFO,  # Set the logging level (DEBUG, INFO, WARNING, ERROR, CRITICAL)
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",  # Log format
    handlers=[
        logging.FileHandler("runner/runner.log"),  # Log to a file
        logging.StreamHandler()  # Log to the console
    ])

app = FastAPI()

app.include_router(base_router)
app.include_router(resource_router)
app.include_router(solution_router)
app.include_router(test_set_router)

