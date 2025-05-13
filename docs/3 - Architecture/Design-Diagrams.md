# System Design Diagrams

This document contains diagrams and explanations of the microservices architecture, data flow, and component interactions.

---

## Table of Contents
1. [High-Level Architecture](#1-high-level-architecture)
2. [Service Interaction Diagram](#2-service-interaction-diagram)
3. [Data Flow Diagram](#3-data-flow-diagram)
4. [Database Design](#4-database-design)
5. [Deployment Diagram](#5-deployment-diagram)
6. [Sequence Diagrams](#6-sequence-diagrams)
7. [Event Flow](#7-event-flow)
8. [Key Design Decisions](#8-key-design-decisions) 

---

## 1. High-Level Architecture
**Diagram**: ![High-Level Architecture](`/images/img.png`)  
**Description**:
- **API Gateway**: Routes requests to respective microservices.
- **Services**:
    - `UserService`: Manages user information & profiles.
    - `ResourceService`: Manages information about courses ad laboratories.
    - `SubmisisonService`: Record submission of user
    - `TesterService`: Run testing process and update result to `SubmissionService`
    - `RunnerService`: Manage and executes operations to interact with `Docker Engine`
    - `FileService`: Handle and async data on `Google Cloud Storage`.
- **Message Broker**: RabbitMQ for async communication.
- **Monitoring**: Prometheus + Grafana for metrics.

**Mermaid**:
```mermaid  
  A[Client] --> B[API Gateway]  
  B --> C[UserService]  
  B --> D[SubmisisonService] 
  B --> E[ResourceService] 
  B --> F[RunnerService]
  E --> H[FileService]
  F --> G[TesterService]
  F --> H[FileService]
  G --> D[SubmisisonService]
```

## 2. Service Interaction Diagram
