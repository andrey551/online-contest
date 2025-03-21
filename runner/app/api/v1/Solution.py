from fastapi import UploadFile, File, Form, APIRouter

from starlette.responses import JSONResponse
import logging

# from Base import app

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
@solution_router.post("/solution")
async def submit_solution(file: UploadFile = File(...),
                           language: str = Form(...),
                           problem_id: str = Form(...)):
    logger.info("received file")

    try:
        if not file.filename.endswith(".zip"):
            return JSONResponse(
                status_code=400,
                content={
                    "error": "File must have .zip extension"
                }
            )
        return JSONResponse(
            status_code=200,
            content={"language": language,
                     "file": file.filename,
                     "id": problem_id}
        )
    except Exception as e:
        return JSONResponse(
            status_code=400,
            content={"error": str(e)}
        )
