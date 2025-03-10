from pydantic import BaseModel

from runner.app.schemas.enums.Language import Language


class ScriptRequest(BaseModel):
    script: str
    language: Language

