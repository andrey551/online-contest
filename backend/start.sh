# 1. Khởi động PostgreSQL
docker-compose up -d

# 2. Kiểm tra network và container PostgreSQL
docker network ls
docker ps

# 3. Chạy Spring Boot service
cd course
docker-compose up --build
