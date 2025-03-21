from pydantic import BaseSettings

class Settings(BaseSettings):
    PROJECT_NAME: str = "Runner"

    class Config:
        env_file = ".env"

    class DatabaseConfig:
        ENGINE: str = "mongodb://localhost:27017"

settings = Settings()