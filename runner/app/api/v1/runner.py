from typing import Optional

from fastapi import APIRouter, UploadFile, File, Form, FastAPI
from starlette.responses import JSONResponse
import logging

logger = logging.getLogger(__name__)

app = FastAPI()

@app.post("/")
async def insert_testset(file: UploadFile = File(...),
                         name: str = Form(...),
                         description: Optional[str] = Form(...)):
    try:
        with open(file.filename, "wb") as f:
            while chunk := file.read(1024):
                f.write(chunk)

        from runner.app.schemas.testset.TestsetRequest import TestsetRequest
        item = TestsetRequest(name=name, description=description)

        return JSONResponse(
            status_code=200,
            content={"item": item}
        )
    except Exception as e:
        return JSONResponse(
            status_code=400,
            content={"error": str(e)}
        )

@app.post("/solution")
async def submit_solution(file: UploadFile = File(...),
                           language: str = Form(...),
                           problem_id: str = Form(...)):
    logger.info("received file")

    try:
        if(not file.filename.endswith(".zip")):
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
