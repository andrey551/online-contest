from pydantic_settings import BaseSettings
from typing import ClassVar


class Settings(BaseSettings):
    PROJECT_NAME: str = "tester"  # Default value for PROJECT_NAME
    ENGINE: str = "mongodb://mongo-db:27017"  # Default value for ENGINE
    ZIP_DIR: str = "/zip"
    EXTRACT_DIR: str = "/extract"

    # Use ClassVar to define model_config
    model_config: ClassVar[dict] = {
        "env_file": ".env",
        "env_file_encoding": "utf-8"
    }


# Create an instance of Settings
settings = Settings()
