
# Stage 1: Build with Gradle
FROM gradle:jdk17-corretto AS builder

# Copy everything to container
COPY . /home/gradle/project
WORKDIR /home/gradle/project

# Bỏ xài ~/.gradle cache
ENV GRADLE_USER_HOME=/build/.gradle

# Build the Spring Boot app with gRPC
RUN gradle bootJar --no-daemon

# Stage 2: Run with JDK runtime only
FROM eclipse-temurin:latest

# Create app directory
WORKDIR /app

# Create config directory
RUN mkdir -p /app/config

# Copy built JAR from the previous stage
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Expose port for gRPC and HTTP
EXPOSE 8087 9090

# Run Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
