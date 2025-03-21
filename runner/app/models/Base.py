from dataclasses import Field
from datetime import datetime

from pydantic import BaseModel
from sqlalchemy import func


class TimeStampBase(BaseModel):
    __abstract__ = True
    created: datetime = Field(default = func.now())
    updated: datetime = Field(default = func.now())