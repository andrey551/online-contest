# Base image
FROM python:latest

# Tạo thư mục làm việc
WORKDIR /app

# Copy dependencies trước (tận dụng cache)
COPY requirements.txt .

# Cài đặt các dependencies
RUN pip install --no-cache-dir -r requirements.txt

ENV PYTHONPATH=/app

# Copy toàn bộ mã nguồn
COPY . .

# Mở cổng cho FastAPI (8000) và gRPC (9191)
EXPOSE 8000
EXPOSE 9191

# Chạy ứng dụng (FastAPI + gRPC)
CMD ["python", "main.py"]

