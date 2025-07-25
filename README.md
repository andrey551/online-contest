# Programming Courses Platform - Overview

## Project Description
Programming Courses Platform is a platform created to automate the process of testing student solutions for subjects related to backend programming. Simply put, the backend application development process includes writing code -> deploying -> testing, this platform automates the deployment and testing process using visualization tools and metrics tools.

## Key Features
- **Programming Course Hosting**: Create and manage programming courses
- **Laboratory Management**: Add, edit, and organize laboratories work
- **Code Submission System**: Accepts code submissions in multiple programming languages
- **Real-time Evaluation**: Automatic judging of submissions with immediate feedback
- **User Ranking**: Leaderboard system to track participant performance
- **Admin Dashboard**: Comprehensive interface for contest management

## Technology Stack
### Frontend
- React.js - Primary frontend framework
- Material-UI (MUI) - UI component library
- Axios - HTTP client for API communication
- Redux Thunk - State and data management
- React Router - Client-side routing

### Backend
- Spring Boot.js & FastAPI- Backend server frameworks
- gRPC - Internal communication tool
- MongoDB & PostgreSQL- Primary database
- Redis - For caching and real-time features
- KeyCloak - Authentication & Authorization mechanism
- Grafana & Promotheus: Metrics & Monitoring
- Google Cloud Storage - Save media & solution files
### Judging System
- Docker - Containerization for safe code execution
- Custom test case evaluation engine

## Repository Structure
````angular2html
online-contest/
├── client/ # Frontend React application
│ ├── public/ # Static assets
│ └── src/ # React source code
│ ├── components/ # Reusable UI components
│ ├── pages/ # Application screens
│ ├── services/ # API service layer
│ └── store/ # State management
├── server/ # Backend Spring Boot application
│ ├── config/ # Configuration files
│ ├── controllers/ # Route controllers
│ ├── models/ # Database models
│ ├── routes/ # API route definitions
│ └── utils/ # Utility functions
└── docs/ # Project documentation
````
## Getting Started
### Prerequisites
- Java 17
- Python
- MongoDB
- PostgreSQL
- Redis
- Docker

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/andrey551/online-contest.git
   cd online-contest/client && npm install
   cd ../server && npm install
   ```
2. Install dependencies for both client and server:
   ```bash
   cd online-contest/client && npm install
   cd ../server && npm install
   ```
3. Set up environment variables (create .env files in both client and server directories)
4. Start the development servers:
   ```bash
   # In client directory
   npm start
   # In server directory
   npm run dev
   ```
### Documentation
Additional documentation can be found in the /docs directory, including:
- API Reference
- Deployment Guide
- Judging System Architecture
- Contribution Guidelines
### Project Status
The project is currently in active development, with regular updates and feature additions. Current focus areas include:
- Enhancing the judging system reliability
- Improving the contest creation workflow
- Adding support for additional programming languages
### License
This project is licensed under the MIT License - see the LICENSE file for details.
