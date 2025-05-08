from fastapi import APIRouter

base_router = APIRouter()


@base_router.get('/runner/health')
def health():
    return {'status': 'ok'}
