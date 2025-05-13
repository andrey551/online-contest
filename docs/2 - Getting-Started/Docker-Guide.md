# Docker Guide for Microservices System

This guide explains how to containerize, run, and manage the [Project Name] microservices using Docker.  
**Each service has its own `Dockerfile`.**

---

## Table of Contents
1. [Prerequisites](#-prerequisites)
2. [Project Structure](#-project-structure)
3. [Version Images](#-version-images)
4. [Running Containers](#-running-containers)
5. [Docker Compose](#-docker-compose-recommended)
6. [Key Commands](#-key-commands)
7. [Important](#-important)

---

## 🛠 Prerequisites
- [Docker](https://docs.docker.com/get-docker/) installed
- [Docker Compose](https://docs.docker.com/compose/install/) (optional)
- Git (for cloning the repo)

---

## 📂 Project Structure

````angular2html
project-root/
├── frontend/
│
├── backend/
│ ├── gateway
│ │  ├── Dockerfile
│ │  └── docker-compose.yml
│ ├── user
│ │  ├── Dockerfile
│ │  └── docker-compose.yml
│ ├── course
│ │  ├── Dockerfile
│ │  └── docker-compose.yml
│ ├── submission
│ │  ├── Dockerfile
│ │  └── docker-compose.yml
│ ├── file
│ │  ├── Dockerfile
│ │  └── docker-compose.yml
│ ├── runner
│ │  ├── Dockerfile
│ │  └── docker-compose.yml
│ ├── tester
│ │  ├── Dockerfile
│ │  └── docker-compose.yml
│ ├── Dockerfile
│ └── docker-compose.yml
````

## 🏗 Version Images recommended
1. *postgres:latest*
2. *mongodb-community-server:7.0-ubi9*
3. *gradle:jdk17-corretto*
4. *python:3.9-slim*
5. *eclipse-temurin:latest*
6. *quay.io/keycloak/keycloak:21.1.1*

You can manual install by command ```` docker pull <image name:image version>````, example: ``` docker pull postgres:latest```

## 🚀 Running Containers
All services need to connect to database, so promise to start container of database first( **backend/docker-compose.yml**)

To start container, move to service directory and run ````docker-compose up --build```` 

### Check running containers:
> docker ps

### Start all services:
> docker-compose up -d

### Stop all services:
> docker-compose down

## 🔑 Key Commands
| Command | Description | 
----------|------------
| docker ps | List running containers|
| docker logs <container-id> | View container logs|
| docker stop <container-id> | Stop a container|
| docker system prune | Clean unused resources|

## 🆘 Important
1. Promise these following volume are created:
   - backend_mongodb-data
   - backend_postgres-data
   - runner_shared_volume
2. Promise docker is running before run container
3. If you use different version of images listed above, don't forget to update it on docker
4. Promise that all services use the same network
