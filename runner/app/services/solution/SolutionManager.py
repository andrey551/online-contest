import logging

from bson import ObjectId
from fastapi import HTTPException

from runner.app.db.Database import solution_collection
from runner.app.models.Solution import SolutionRequest, to_solution
from starlette.responses import JSONResponse

from runner.app.services.resource.ResourceService import get_resource_by_problem_id
from runner.app.services.docker.DockerRunner import runner

logger = logging.getLogger(__name__)

async def insert_solution(solution_req : SolutionRequest, container_id: str):
    try:
        solution = to_solution(solution_req, container_id)

        solution_ret = await solution_collection.insert_one(solution.dict())

        if solution_ret.inserted_id:
            return JSONResponse(
                status_code=200,
                content={
                    "status": "success",
                    "solution_id": str(solution_ret.inserted_id)
                }
            )
        else:
            return JSONResponse(
                status_code=400,
                content={
                    "status": "failure",
                    "message": "Failed to insert solution"
                }
            )

    except Exception as e:
        logger.error(e)

async def get_solution(solution_id:str) :
    try:
        solution = await solution_collection.find_one({"_id": ObjectId(solution_id)})
        return solution
    except Exception as e:
        logger.error(e)
        raise HTTPException(status_code=404, detail="Solution not found")

async def containerize_solution(solution_req : SolutionRequest):
    try:
        problem_id_str = solution_req.problem_id
        resource = await get_resource_by_problem_id(problem_id_str)

        if resource is None:
            raise Exception("Resource not found")

        container = await runner.run_project_in_docker(resource["list_images"][0], solution_req)
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

async def do_test(solution_id: str):
    solution = await get_solution(solution_id)
    if solution is None:
        raise HTTPException(status_code=404, detail="Solution not found")


