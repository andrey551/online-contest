import logging

from fastapi import HTTPException

from runner.app.db.Database import solution_collection
from runner.app.schemas.requests.Solution import SolutionRequest, to_solution
from starlette.responses import JSONResponse

from runner.app.services.ResourceService import get_resource, get_resource_by_problem_id
from runner.app.services.Runner import runner

logger = logging.getLogger(__name__)

async def insert_solution(solution_req : SolutionRequest):
    try:
        solution = to_solution(solution_req)

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

async def containerize_solution(solution_req : SolutionRequest):

    try:
        problem_id_str = solution_req.problem_id
        resource = await get_resource_by_problem_id(problem_id_str)

        if resource is None:
            raise Exception("Resource not found")

        container = await runner.run_project_in_docker(resource["list_images"][0], solution_req)

        return container.id
    except AttributeError as e:
        logger.error(f"Model parsing error: {str(e)}")
        raise HTTPException(
            status_code=422,
            detail="Invalid request format. Expected SolutionRequest model."
        )
    except Exception as e:
        logger.error(e)

