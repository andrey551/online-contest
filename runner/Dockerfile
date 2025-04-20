# Base image
FROM python:latest

# Set working directory
WORKDIR /app

# Copy dependencies first to leverage caching
COPY requirements.txt .

# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

ENV PYTHONPATH=/app

# Copy the rest of the source code
COPY . .

# Expose ports for FastAPI (8000) and gRPC (9191)
EXPOSE 8000
EXPOSE 9191

# Start the FastAPI app using uvicorn
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]
