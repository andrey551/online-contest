from datetime import datetime
from pathlib import Path

from fastapi import APIRouter, UploadFile, File, Form

from starlette.responses import JSONResponse
import logging

from runner.app.models.Solution import SolutionRequest
from runner.app.services.solution.SolutionManager import containerize_solution, insert_solution
from runner.app.services.solution.SolutionRunner import run_and_check_solution

logger = logging.getLogger(__name__)
solution_router = APIRouter()

""""
Solution API: To control solution of students
"""


""""
Usage: Receive backend system solution from student
Parameters:
    File: A .zip file contain backend system solution
    Language: Programming language of solution
    Problem ID: Problem ID of solution 
Return: 
"""

@solution_router.post("/api/v1/solution")
async def submit_solution(problem_id: str = Form(...),
                          author_id: str = Form(...),
                          file: UploadFile = File(...)):

    try:
        current_dir = Path(__file__).parent.parent.parent.parent.resolve()
        solution_request = SolutionRequest(problem_id=problem_id,
                                            author_id=author_id,
                                            file_name=file.filename,
                                           crt_dir=str(current_dir).strip("/\\"),
                                           submit_time=datetime.now())
        if not file.filename.endswith(".zip"):
            return JSONResponse(
                status_code=400,
                content={
                    "error": "File must have .zip format"
                }
            )

        # Create file path for saving zip file
        target_dir = current_dir / solution_request.zip_path.strip("/\\")
        target_dir.mkdir(parents=True, exist_ok=True)
        file_path = target_dir / file.filename

        # Write .zip file to
        with open(file_path, "wb") as buffer:
            buffer.write(await file.read())

        container_id = await containerize_solution(solution_request)
        logger.info(f"Container ID: {container_id}")
        solution = await insert_solution(solution_request, str(container_id))

        return solution
    except Exception as e:
        return JSONResponse(
            status_code=400,
            content={"error": str(e)}
        )
@solution_router.put("/api/v1/solution/{solution_id}")
async def check_solution(solution_id):
    try:
        return await run_and_check_solution(solution_id)
    except Exception as e:
        return JSONResponse(status_code=400, content={"error": str(e)})

