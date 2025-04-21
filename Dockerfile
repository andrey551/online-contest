# postgres.Dockerfile
FROM postgres:latest

# Thiết lập biến môi trường cho user, password và db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=05052001
ENV POSTGRES_DB=contest

# Cổng mặc định
EXPOSE 5432