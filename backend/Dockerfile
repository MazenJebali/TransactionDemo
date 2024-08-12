# Use the official OpenJDK 22 image from the Docker Hub
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the build context to the container
COPY target/test-0.0.1-SNAPSHOT.jar /app/test-0.0.1-SNAPSHOT.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "test-0.0.1-SNAPSHOT.jar"]
