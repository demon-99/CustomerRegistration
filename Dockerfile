# Use official OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set working directory in container
WORKDIR /app

# Copy the JAR file from target directory (after building the project)
COPY target/User-Registration-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
