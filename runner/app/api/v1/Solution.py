import datetime
import logging
from pathlib import Path
from app.services.docker.DockerManager import create_submission
from app.services.solution.SolutionManager import containerize_solution
from app.services.solution.SolutionRunner import run_and_check_solution
from app.models.Solution import SolutionRequest
from app.services.solution.SolutionManager import update_if_exist_or_create
from fastapi import APIRouter, UploadFile, File, Form
from starlette.responses import JSONResponse

from app.utils.port import get_free_port

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
async def submit_solution(laboratory_id: str = Form(...),
                          author_id: str = Form(...),
                          file: UploadFile = File(...)):

    try:
        current_dir = Path(__file__).parent.parent.parent.parent.resolve()
        solution_request = SolutionRequest(laboratory_id=laboratory_id,
                                           author_id=author_id,
                                           file_name=file.filename,
                                           crt_dir=str(current_dir).strip("/\\"),
                                           submit_time=datetime.now(),
                                           port=get_free_port())
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
        solution = await update_if_exist_or_create(solution_request, str(container_id))
        submission_id = await create_submission(user_id=author_id,
                                                laboratory_id=laboratory_id)

        return JSONResponse(
            status_code=200,
            content={
                "submission_id": submission_id["submissionId"],
                "solution_id": solution
            }
        )

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
