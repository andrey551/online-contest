from pydantic import BaseSettings

class Settings(BaseSettings):
    PROJECT_NAME: str = "Runner"
    DATABASE_URL: str

    class Config:
        env_file = ".env"

settings = Settings()