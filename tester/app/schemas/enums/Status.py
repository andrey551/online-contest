from enum import Enum


class Status(Enum):
    PASSED = 'passed'
    FAILED = 'failed'
    MEMORY_EXCEEDED = 'memory_exceeded'
    TIMED_OUT = 'timed_out'
    COMPILE_ERROR = 'compile_error'
