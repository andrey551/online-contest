# Stage 1: Build with Gradle
FROM gradle:7.4.2-jdk17-alpine AS builder

# Copy everything to container
COPY . /home/gradle/project
WORKDIR /home/gradle/project

# Build the Spring Boot app with gRPC
RUN gradle bootJar --no-daemon

# Stage 2: Run with JDK runtime only
FROM eclipse-temurin:17-jdk

# Create app directory
WORKDIR /app

# Copy built JAR from the previous stage
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Expose port for gRPC and HTTP
EXPOSE 8082 8082

# Run Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
