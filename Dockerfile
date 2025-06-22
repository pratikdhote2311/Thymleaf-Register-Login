# -------- Stage 1: Build the application --------
FROM ubuntu:latest AS build

# Install dependencies
RUN apt-get update && apt-get install -y openjdk-17-jdk wget unzip

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Grant execute permission to gradlew
RUN chmod +x ./gradlew

# Build the application using Gradle
RUN ./gradlew bootJar --no-daemon

# -------- Stage 2: Run the application --------
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Expose the port your Spring Boot app uses
EXPOSE 8080

# Copy the built jar from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
