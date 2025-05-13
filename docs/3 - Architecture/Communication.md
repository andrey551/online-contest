# Microservices Communication

This document outlines the communication protocols, patterns, and contracts between services in the system.

## Table of Contents
1. [Communication Protocols](#communication-protocols)
2. [Synchronous Communication](#synchronous-communication)
3. [Asynchronous Communication](#asynchronous-communication)
4. [Event-Driven Architecture](#event-driven-architecture)
5. [API Contracts](#api-contracts)
6. [Error Handling](#error-handling)
7. [Service Discovery](#service-discovery)
8. [Security](#security)
9. [Monitoring & Logging](#monitoring--logging)  

---

## 1. Communication Protocols
- **HTTP/REST**: Used for synchronous requests (e.g., service-to-service calls).
- **gRPC**: Used for high-performance, low-latency internal communication.
- **Message Brokers**:
    - **RabbitMQ**: Queue-based task processing.
- **WebSockets**: Real-time updates on frontend.
---

## 2. Synchronous Communication
**When to Use**: Immediate responses required (e.g., fetching user data), transport data between services.  
**Patterns**:
- **Direct HTTP Calls**:
    - Example: `Gateway → UserService` (REST).
    - Timeout: Default 2s.
- **gRPC**:
    - Protocol Buffers schema location: `/backend/protos/*.proto`. 

## 3. Asynchronous Communication
**When to Use**: Decoupled, event-driven workflows (e.g., order processing).
**Patterns**:

- **Queues (RabbitMQ)**:
    - Queues: `email_tasks`, `solution_update`.

## 4. Event-Driven Architecture
**Event Catalog:**

| Event Name | Producer | Consumer                             |
-------------|----------|--------------------------------------
|`laboratory.created`|`CourseService`| `RunnerService`, `TesterService`     |
|`test.run`|`TesterService`| `RunnerService`, `SubmissionService` |
|`Sulution.submit`|`RunnerService`|`FileService`, `SubmissionService`|

***Idempotency***: Events include a correlation_id for deduplication.

## 5. API Contracts

- **OpenAPI/Swagger**: Defined in /docs/swagger.yaml per service.

- **Schema Registry**: Avro schemas in /schemas/.

- **Versioning**: APIs use semantic versioning (e.g., v1, v2).

## 6. Error Handling
- **HTTP Status Codes**:
    - **429 Too Many Requests**: Implement retry with exponential backoff.
    - **503 Service Unavailable**: Circuit breaker pattern (fallback logic).
- **gRPC Status Codes**:
    - `OK: 0`
    - `CANCELLED: 1`
    - `INVALID_ARGUMENT: 3`
    - `NOT_FOUND: 5`
    - `UNAVAILABLE: 14`
    - `UNAUTHENTICATED: 16`
