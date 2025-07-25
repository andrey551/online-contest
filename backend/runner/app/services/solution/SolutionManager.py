import logging
from datetime import datetime

from app.db.Database import solution_collection
from app.models.Solution import SolutionRequest, to_solution
from app.services.docker.DockerRunner import runner
from app.services.resource.ResourceService import get_resource_by_laboratory_id
from bson import ObjectId
from fastapi import HTTPException

logger = logging.getLogger(__name__)


async def update_if_exist_or_create(solution_req: SolutionRequest, container_id: str):
    logger.info("go there")
    try:
        solution = to_solution(solution_req, container_id)

        exist_solution = await (solution_collection
                                .find_one({
                                           "laboratory_id": solution.laboratory_id,
                                           "author_id": solution.author_id}))

        if exist_solution:
            (solution_collection
             .update_one({"_id": ObjectId(exist_solution["_id"])},
                         {"$set": {"file_name": solution.file_name,
                                   "container_id": solution.container_id,
                                   "submit_time": datetime.now()}}))
            logger.info(f"Updated {solution.laboratory_id} to {solution.author_id}")
            return str(exist_solution["_id"])
        else:
            logger.info(f"Created {solution.laboratory_id} to {solution.author_id}")
            return await insert_solution(solution_req, container_id)

    except Exception as e:
        logger.error(e)


async def insert_solution(solution_req: SolutionRequest, container_id: str):
    try:
        solution = to_solution(solution_req, container_id)

        solution_ret = await solution_collection.insert_one(solution.dict())

        if solution_ret.inserted_id:

            return str(solution_ret.inserted_id)

        else:
            raise HTTPException(status_code=404, detail="Solution not found")

    except Exception as e:
        logger.error(e)


async def get_solution(solution_id: str):
    try:
        solution = await solution_collection.find_one({"_id": ObjectId(solution_id)})
        return solution
    except Exception as e:
        logger.error(e)
        raise HTTPException(status_code=404, detail="Solution not found")


async def containerize_solution(solution_req: SolutionRequest):
    try:
        laboratory_id_str = solution_req.laboratory_id
        resource = await get_resource_by_laboratory_id(laboratory_id_str)

        if resource is None:
            raise Exception("Resource not found")
        else:
            logger.info(f"Got resource {resource}")

        container = await runner.run_project_in_docker(resource["list_images"][0],
                                                       solution_req)
        logger.debug(container)
        return container
    except AttributeError as e:
        logger.error(f"Model parsing error: {str(e)}")
        raise HTTPException(
            status_code=422,
            detail="Invalid request format. Expected SolutionRequest model."
        )
    except Exception as e:
        logger.error(e)


async def get_container_id_by_laboratory_id(laboratory_id: str):
    try:
        resource = await solution_collection.find_one({"laboratory_id": laboratory_id})
        if resource is None:
            raise Exception("Resource not found")
        return resource["container_id"]
    except Exception as e:
        logger.error(e)
        raise Exception("Solution not found")


async def get_container_id_by_solution_id(solution_id: str):
    try:
        resource = await solution_collection.find_one({"_id": ObjectId(solution_id)})
        if resource is None:
            raise Exception("Resource not found")
        return resource["container_id"]
    except Exception as e:
        logger.error(e)
        raise HTTPException(status_code=404, detail="Solution not found")


async def do_test(solution_id: str):
    solution = await get_solution(solution_id)
    if solution is None:
        raise HTTPException(status_code=404, detail="Solution not found")
