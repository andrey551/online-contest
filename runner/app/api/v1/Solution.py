from datetime import datetime
from pathlib import Path

from fastapi import APIRouter, UploadFile, File, Form

from starlette.responses import JSONResponse
import logging

from runner.app.models.Solution import SolutionRequest
from runner.app.services.SolutionManager import containerize_solution, insert_solution

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


        target_dir = current_dir / solution_request.zip_path.strip("/\\")
        target_dir.mkdir(parents=True, exist_ok=True)
        file_path = target_dir / file.filename

        with open(file_path, "wb") as buffer:
            buffer.write(await file.read())

        await containerize_solution(solution_request)
        solution = await insert_solution(solution_request)

        return solution
    except Exception as e:
        return JSONResponse(
            status_code=400,
            content={"error": str(e)}
        )
