# === Stage 1: Build the application ===
FROM gradle:7.4.2-jdk17 AS build

USER root                                       # <-- required to avoid permission errors

WORKDIR /home/gradle/project

COPY . .

RUN gradle clean bootJar --no-daemon


# === Stage 2: Run the app ===
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
