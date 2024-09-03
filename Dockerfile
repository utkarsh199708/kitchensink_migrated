# Use an official Java JDK 21 runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the container
COPY . /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Build the application
RUN mvn clean package -DskipTests

# Expose the port the application runs on

EXPOSE 8081

# Run the application
CMD ["java", "-jar", "target/kitchensink_migrated-0.0.1-SNAPSHOT.jar"]