-- Courses data
INSERT INTO course (_id, description, directory, semester, teacher_id, teacher_name, title)
VALUES
    ('b29a7aa3-cc0a-4e80-9f71-99f58cb32d67', 'Introduction to Algorithms and Data Structures', 'cs101', 'Fall 2024', 'a9c24e8e-57db-4a82-a5e5-81970c5db7a2', 'Dr. Alice Johnson', 'Algorithms 101'),
    ('c541ba62-8f52-4a43-a2ef-5e7e1b48b1c1', 'Object-Oriented Programming with Java', 'cs102', 'Spring 2025', 'a9c24e8e-57db-4a82-a5e5-81970c5db7a2', 'Dr. Alice Johnson', 'OOP in Java'),
    ('30f4b0d9-2dbf-4c11-8f8a-9fcd4511d93e', 'Web Development with HTML, CSS, and JavaScript', 'cs103', 'Fall 2024', '5e3f4bd1-4bb1-4c62-9ac3-c1bd4fc5f68a', 'Mr. Bob Smith', 'Frontend Basics'),
    ('8a96b127-8ecf-4aa3-b9c7-03e643d5c333', 'Database Systems and SQL', 'cs104', 'Spring 2025', 'e37c79b5-98cd-409e-94e0-e945aeb9fa6f', 'Prof. Carol White', 'Database Design'),
    ('ee882b49-b4cd-4576-a4ec-929f54060f31', 'Operating Systems Fundamentals', 'cs105', 'Fall 2024', 'd471b3f3-4531-4bc1-a7aa-802e325d6a38', 'Dr. David Green', 'Intro to OS'),
    ('9d6f4a5c-1e02-4b8c-a72e-7c9ad6f1e97a', 'Introduction to Computer Networks', 'cs106', 'Spring 2025', '2c97ea9b-bb3f-4c96-97f6-8205b15e3d3e', 'Dr. Eva Martinez', 'Computer Networks'),
    ('d5cb6e87-56e3-4e3f-b090-1a29b90812cc', 'Software Engineering Principles', 'cs107', 'Fall 2024', 'f60d35bb-6e28-42d5-8b0a-1f8fa13857e3', 'Prof. Frank Nolan', 'Software Engineering'),
    ('b7ad1c13-9e87-4562-991b-31f7a6e7b7a1', 'Mobile Application Development', 'cs108', 'Spring 2025', '8bc0c623-f982-4986-927e-c42aa76325c5', 'Dr. Grace Lee', 'Mobile Dev'),
    ('c4f2cb8d-7559-4f87-a9a9-7a94be8820e9', 'Cloud Computing and Virtualization', 'cs109', 'Fall 2024', '47dfcf11-9512-4a44-83e3-d8417d57f5a2', 'Mr. Henry Tran', 'Cloud Computing'),
    ('e018f7a1-81c7-4e0b-8ae6-83920674a1d5', 'Cybersecurity Fundamentals', 'cs110', 'Spring 2025', '26f4b92c-c382-4a7b-90d4-68f0b52bfb63', 'Prof. Isabella Chen', 'Cybersecurity Basics'),
    ('f17f31a4-3b7f-4b9b-b9b3-1d6d62bb2a0e', 'Advanced Algorithms and Problem Solving', 'cs111', 'Fall 2024', 'a9c24e8e-57db-4a82-a5e5-81970c5db7a2', 'Dr. Alice Johnson', 'Advanced Algorithms'),
    ('c8f4d0fc-0f5a-49d7-bb94-40bba3d54758', 'Data Structures in Depth', 'cs112', 'Spring 2025', 'a9c24e8e-57db-4a82-a5e5-81970c5db7a2', 'Dr. Alice Johnson', 'Data Structures II'),
    ('963fcf1a-3265-4db6-b89b-5a07958e3760', 'Competitive Programming Workshop', 'cs113', 'Fall 2024', 'a9c24e8e-57db-4a82-a5e5-81970c5db7a2', 'Dr. Alice Johnson', 'CP Workshop'),
    ('79e3a36e-3494-46d4-86b0-20349e7d10c7', 'Introduction to Machine Learning', 'cs114', 'Spring 2025', 'a9c24e8e-57db-4a82-a5e5-81970c5db7a2', 'Dr. Alice Johnson', 'Machine Learning'),
    ('07f4c8f9-d3fa-4f61-8b04-9242f54d9ee1', 'Research Methods in Computer Science', 'cs115', 'Fall 2024', 'a9c24e8e-57db-4a82-a5e5-81970c5db7a2', 'Dr. Alice Johnson', 'CS Research');

--  Laboratories data

INSERT INTO laboratory (_id, created, deadline, description, directory, tags, title, course_id) VALUES
(gen_random_uuid(), '2024-01-15', '2024-02-15', 'Introduction to basic programming concepts', '/labs/intro-prog', 'beginner,programming', 'Lab 1: Programming Basics', 'b29a7aa3-cc0a-4e80-9f71-99f58cb32d67'),
(gen_random_uuid(), '2024-02-16', '2024-03-15', 'Working with variables and data types', '/labs/variables', 'variables,datatypes', 'Lab 2: Variables and Data Types', 'b29a7aa3-cc0a-4e80-9f71-99f58cb32d67'),
(gen_random_uuid(), '2024-03-16', '2024-04-15', 'Control structures and loops', '/labs/control-flow', 'loops,conditionals', 'Lab 3: Control Flow', 'b29a7aa3-cc0a-4e80-9f71-99f58cb32d67'),
(gen_random_uuid(), '2024-04-16', '2024-05-15', 'Functions and modular programming', '/labs/functions', 'functions,modular', 'Lab 4: Functions', 'b29a7aa3-cc0a-4e80-9f71-99f58cb32d67'),
(gen_random_uuid(), '2024-05-16', '2024-06-15', 'Final project implementation', '/labs/final-project', 'project,capstone', 'Lab 5: Final Project', 'b29a7aa3-cc0a-4e80-9f71-99f58cb32d67'),
(gen_random_uuid(), '2024-01-10', '2024-02-10', 'Database design and normalization', '/labs/db-design', 'design,normalization', 'Lab 1: Database Design', 'c541ba62-8f52-4a43-a2ef-5e7e1b48b1c1'),
(gen_random_uuid(), '2024-02-11', '2024-03-10', 'SQL queries and CRUD operations', '/labs/sql-crud', 'sql,queries', 'Lab 2: SQL Fundamentals', 'c541ba62-8f52-4a43-a2ef-5e7e1b48b1c1'),
(gen_random_uuid(), '2024-03-11', '2024-04-10', 'Advanced SQL queries with joins', '/labs/sql-joins', 'joins,advanced', 'Lab 3: SQL Joins', 'c541ba62-8f52-4a43-a2ef-5e7e1b48b1c1'),
(gen_random_uuid(), '2024-04-11', '2024-05-10', 'Indexes and query optimization', '/labs/optimization', 'indexes,performance', 'Lab 4: Query Optimization', 'c541ba62-8f52-4a43-a2ef-5e7e1b48b1c1'),
(gen_random_uuid(), '2024-05-11', '2024-06-10', 'Transactions and concurrency control', '/labs/transactions', 'transactions,locking', 'Lab 5: Transactions', 'c541ba62-8f52-4a43-a2ef-5e7e1b48b1c1'),
(gen_random_uuid(), '2024-06-11', '2024-07-10', 'Database administration basics', '/labs/admin', 'administration,backup', 'Lab 6: Database Admin', 'c541ba62-8f52-4a43-a2ef-5e7e1b48b1c1'),
(gen_random_uuid(), '2024-01-20', '2024-02-20', 'Algorithm complexity analysis', '/labs/complexity', 'big-o,analysis', 'Lab 1: Complexity Analysis', '30f4b0d9-2dbf-4c11-8f8a-9fcd4511d93e'),
(gen_random_uuid(), '2024-02-21', '2024-03-20', 'Sorting algorithms implementation', '/labs/sorting', 'sorting,algorithms', 'Lab 2: Sorting Algorithms', '30f4b0d9-2dbf-4c11-8f8a-9fcd4511d93e'),
(gen_random_uuid(), '2024-03-21', '2024-04-20', 'Graph algorithms and traversal', '/labs/graphs', 'graphs,traversal', 'Lab 3: Graph Algorithms', '30f4b0d9-2dbf-4c11-8f8a-9fcd4511d93e'),
(gen_random_uuid(), '2024-04-21', '2024-05-20', 'Dynamic programming problems', '/labs/dynamic', 'dynamic,programming', 'Lab 4: Dynamic Programming', '30f4b0d9-2dbf-4c11-8f8a-9fcd4511d93e'),
(gen_random_uuid(), '2024-01-05', '2024-02-05', 'Introduction to machine learning concepts', '/labs/ml-intro', 'intro,concepts', 'Lab 1: ML Introduction', '8a96b127-8ecf-4aa3-b9c7-03e643d5c333'),
(gen_random_uuid(), '2024-02-06', '2024-03-05', 'Linear regression implementation', '/labs/linear-reg', 'regression,supervised', 'Lab 2: Linear Regression', '8a96b127-8ecf-4aa3-b9c7-03e643d5c333'),
(gen_random_uuid(), '2024-03-06', '2024-04-05', 'Classification with decision trees', '/labs/decision-trees', 'classification,trees', 'Lab 3: Decision Trees', '8a96b127-8ecf-4aa3-b9c7-03e643d5c333'),
(gen_random_uuid(), '2024-04-06', '2024-05-05', 'Neural networks fundamentals', '/labs/neural-nets', 'neural,deep-learning', 'Lab 4: Neural Networks', '8a96b127-8ecf-4aa3-b9c7-03e643d5c333'),
(gen_random_uuid(), '2024-05-06', '2024-06-05', 'Model evaluation techniques', '/labs/model-eval', 'evaluation,metrics', 'Lab 5: Model Evaluation', '8a96b127-8ecf-4aa3-b9c7-03e643d5c333'),
(gen_random_uuid(), '2024-01-12', '2024-02-12', 'Mobile UI development basics', '/labs/mobile-ui', 'ui,layouts', 'Lab 1: Mobile UI', 'ee882b49-b4cd-4576-a4ec-929f54060f31'),
(gen_random_uuid(), '2024-02-13', '2024-03-12', 'Working with device sensors', '/labs/sensors', 'sensors,accelerometer', 'Lab 2: Device Sensors', 'ee882b49-b4cd-4576-a4ec-929f54060f31'),
(gen_random_uuid(), '2024-03-13', '2024-04-12', 'Data persistence on mobile', '/labs/persistence', 'storage,database', 'Lab 3: Data Persistence', 'ee882b49-b4cd-4576-a4ec-929f54060f31'),
(gen_random_uuid(), '2024-04-13', '2024-05-12', 'Network communication', '/labs/networking', 'api,http', 'Lab 4: Networking', 'ee882b49-b4cd-4576-a4ec-929f54060f31'),
(gen_random_uuid(), '2024-05-13', '2024-06-12', 'Performance optimization', '/labs/performance', 'optimization,profiling', 'Lab 5: Performance', 'ee882b49-b4cd-4576-a4ec-929f54060f31'),
(gen_random_uuid(), '2024-06-13', '2024-07-12', 'Publishing to app stores', '/labs/publishing', 'store,deployment', 'Lab 6: App Publishing', 'ee882b49-b4cd-4576-a4ec-929f54060f31'),
(gen_random_uuid(), '2024-01-08', '2024-02-08', 'Cloud computing fundamentals', '/labs/cloud-intro', 'intro,concepts', 'Lab 1: Cloud Basics', '9d6f4a5c-1e02-4b8c-a72e-7c9ad6f1e97a'),
(gen_random_uuid(), '2024-02-09', '2024-03-08', 'Virtual machines deployment', '/labs/vms', 'compute,instances', 'Lab 2: Virtual Machines', '9d6f4a5c-1e02-4b8c-a72e-7c9ad6f1e97a'),
(gen_random_uuid(), '2024-03-09', '2024-04-08', 'Object storage implementation', '/labs/storage', 'storage,buckets', 'Lab 3: Cloud Storage', '9d6f4a5c-1e02-4b8c-a72e-7c9ad6f1e97a'),
(gen_random_uuid(), '2024-04-09', '2024-05-08', 'Serverless functions', '/labs/serverless', 'functions,faas', 'Lab 4: Serverless', '9d6f4a5c-1e02-4b8c-a72e-7c9ad6f1e97a'),
(gen_random_uuid(), '2024-05-09', '2024-06-08', 'Cloud security best practices', '/labs/security', 'security,iam', 'Lab 5: Cloud Security', '9d6f4a5c-1e02-4b8c-a72e-7c9ad6f1e97a'),
(gen_random_uuid(), '2024-01-18', '2024-02-18', 'Network scanning techniques', '/labs/scanning', 'scanning,nmap', 'Lab 1: Network Scanning', 'd5cb6e87-56e3-4e3f-b090-1a29b90812cc'),
(gen_random_uuid(), '2024-02-19', '2024-03-18', 'Vulnerability assessment', '/labs/vuln-assessment', 'vulnerability,scanning', 'Lab 2: Vulnerability Assessment', 'd5cb6e87-56e3-4e3f-b090-1a29b90812cc'),
(gen_random_uuid(), '2024-03-19', '2024-04-18', 'Penetration testing basics', '/labs/pen-testing', 'pentesting,exploits', 'Lab 3: Penetration Testing', 'd5cb6e87-56e3-4e3f-b090-1a29b90812cc'),
(gen_random_uuid(), '2024-04-19', '2024-05-18', 'Security tools and frameworks', '/labs/security-tools', 'tools,metasploit', 'Lab 4: Security Tools', 'd5cb6e87-56e3-4e3f-b090-1a29b90812cc'),
(gen_random_uuid(), '2024-01-22', '2024-02-22', 'Data visualization principles', '/labs/viz-principles', 'principles,design', 'Lab 1: Visualization Principles', 'b7ad1c13-9e87-4562-991b-31f7a6e7b7a1'),
(gen_random_uuid(), '2024-02-23', '2024-03-22', 'Working with D3.js', '/labs/d3-intro', 'd3,charts', 'Lab 2: D3.js Basics', 'b7ad1c13-9e87-4562-991b-31f7a6e7b7a1'),
(gen_random_uuid(), '2024-03-23', '2024-04-22', 'Interactive visualizations', '/labs/interactive-viz', 'interactive,events', 'Lab 3: Interactive Viz', 'b7ad1c13-9e87-4562-991b-31f7a6e7b7a1'),
(gen_random_uuid(), '2024-04-23', '2024-05-22', 'Dashboard design', '/labs/dashboards', 'dashboard,layout', 'Lab 4: Dashboards', 'b7ad1c13-9e87-4562-991b-31f7a6e7b7a1'),
(gen_random_uuid(), '2024-05-23', '2024-06-22', 'Visualization for big data', '/labs/big-data-viz', 'big-data,sampling', 'Lab 5: Big Data Viz', 'b7ad1c13-9e87-4562-991b-31f7a6e7b7a1'),
(gen_random_uuid(), '2024-01-25', '2024-02-25', 'Blockchain fundamentals', '/labs/blockchain-intro', 'intro,concepts', 'Lab 1: Blockchain Basics', 'c4f2cb8d-7559-4f87-a9a9-7a94be8820e9'),
(gen_random_uuid(), '2024-02-26', '2024-03-25', 'Smart contracts development', '/labs/smart-contracts', 'solidity,contracts', 'Lab 2: Smart Contracts', 'c4f2cb8d-7559-4f87-a9a9-7a94be8820e9'),
(gen_random_uuid(), '2024-03-26', '2024-04-25', 'Decentralized applications', '/labs/dapps', 'dapps,web3', 'Lab 3: DApps', 'c4f2cb8d-7559-4f87-a9a9-7a94be8820e9'),
(gen_random_uuid(), '2024-04-26', '2024-05-25', 'Token implementation', '/labs/tokens', 'tokens,erc20', 'Lab 4: Tokens', 'c4f2cb8d-7559-4f87-a9a9-7a94be8820e9'),
(gen_random_uuid(), '2024-05-26', '2024-06-25', 'Blockchain security', '/labs/blockchain-security', 'security,auditing', 'Lab 5: Blockchain Security', 'c4f2cb8d-7559-4f87-a9a9-7a94be8820e9'),
(gen_random_uuid(), '2024-01-28', '2024-02-28', 'UI design principles', '/labs/ui-principles', 'principles,layout', 'Lab 1: UI Principles', 'e018f7a1-81c7-4e0b-8ae6-83920674a1d5'),
(gen_random_uuid(), '2024-02-29', '2024-03-28', 'Wireframing and prototyping', '/labs/wireframing', 'wireframes,prototypes', 'Lab 2: Wireframing', 'e018f7a1-81c7-4e0b-8ae6-83920674a1d5'),
(gen_random_uuid(), '2024-03-29', '2024-04-28', 'Color theory and typography', '/labs/color-typography', 'color,typography', 'Lab 3: Color & Type', 'e018f7a1-81c7-4e0b-8ae6-83920674a1d5'),
(gen_random_uuid(), '2024-04-29', '2024-05-28', 'User testing methods', '/labs/user-testing', 'testing,feedback', 'Lab 4: User Testing', 'e018f7a1-81c7-4e0b-8ae6-83920674a1d5'),
(gen_random_uuid(), '2024-05-29', '2024-06-28', 'Design systems', '/labs/design-systems', 'systems,components', 'Lab 5: Design Systems', 'e018f7a1-81c7-4e0b-8ae6-83920674a1d5');

-- Sample of laboratory description written in markdown( can be replaced for laboratory.description)
'# 🚀 Backend API Laboratory: FastAPI + MongoDB

**🔖 Laboratory #3**
**📅 Due:** 2023-12-15
**🏷️ Tags:** `backend` `fastapi` `mongodb` `rest-api` `python`
**⏱️ Max Response Time:** 500ms
**💾 Max Memory Usage:** 512MB
**📊 Expected Load:** 100+ concurrent users

## 🎯 Purpose
Build a high-performance task management API that demonstrates:
- 🏗️ Modern API architecture
- 🛡️ Secure authentication
- 🚦 Performance optimization
- 📝 Comprehensive documentation
- 🧩 Modular design

## 🛠️ Technical Specifications

### ⚙️ System Requirements
| Metric               | Requirement          | Icon |
|----------------------|----------------------|------|
| **Response Time**    | ≤500ms (p95)        | ⏱️   |
| **Memory Usage**     | ≤512MB under load   | 💾   |
| **Concurrent Users** | Supports 100+       | 👥   |
| **Availability**     | 99% uptime          | 🟢   |

## 🔌 API Endpoints

### 🔐 Authentication
| Endpoint               | Method | Description                     | Parameters               | Icon |
|------------------------|--------|---------------------------------|--------------------------|------|
| `POST /auth/register`  | POST   | User registration               | email, password, name    | ✏️   |
| `POST /auth/login`     | POST   | JWT token generation            | email, password          | 🔑   |
| `POST /auth/refresh`   | POST   | Refresh access token            | refresh_token            | 🔄   |

### 👤 User Management
| Endpoint               | Method | Description                     | Auth Required | Icon |
|------------------------|--------|---------------------------------|---------------|------|
| `GET /users/me`        | GET    | Get current user profile        | Yes           | 👤   |
| `GET /users/{id}`      | GET    | Get user by ID (admin only)     | Yes           | 🕵️  |

### ✅ Task Management
| Endpoint               | Method | Description                     | Query Params             | Icon |
|------------------------|--------|---------------------------------|--------------------------|------|
| `POST /tasks/`         | POST   | Create new task                 | title, description       | ➕   |
| `GET /tasks/`          | GET    | List all tasks (paginated)      | page, limit, status      | 📋   |
| `GET /tasks/{id}`      | GET    | Get task details                | -                        | 🔍   |
| `PUT /tasks/{id}`      | PUT    | Update task                     | title, description, status| ✏️   |
| `DELETE /tasks/{id}`   | DELETE | Delete task                     | -                        | 🗑️   |

## 🚦 Performance Requirements
```python
# Example performance check
from fastapi import HTTPException

@app.get("/tasks/")
async def list_tasks():
    if psutil.virtual_memory().used > 512 * 1024 * 1024:
        raise HTTPException(500, "Memory limit exceeded")
    # ... implementation'