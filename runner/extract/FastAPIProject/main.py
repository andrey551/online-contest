from fastapi import FastAPI

app = FastAPI()


@app.post("/")
async def root():
    return {"message": "Hello World"}


@app.post("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}
