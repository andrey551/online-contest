import json
from enum import Enum


class Status(Enum):
    PASSED = 'passed'
    FAILED = 'failed'
    MEMORY_EXCEEDED = 'memory_exceeded'
    TIMED_OUT = 'timed_out'
    COMPILE_ERROR = 'compile_error'


class CustomJSONEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Enum):
            return obj.value
        if hasattr(obj, '__dict__'):
            return obj.__dict__
        return super().default(obj)
