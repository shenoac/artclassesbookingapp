# Use a lightweight OpenJDK base image
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and Maven-related files (from root)
COPY ../mvnw /app/mvnw
COPY ../.mvn /app/.mvn

# Copy the booking module's pom.xml and source code
COPY booking/pom.xml /app/pom.xml
COPY booking/src /app/src

# Ensure the Maven wrapper has execute permissions
RUN chmod +x /app/mvnw

# Run the Maven build for the booking module only
RUN ./mvnw package -DskipTests

# Expose the port on which Quarkus will run (default is 8080)
EXPOSE 8080

# Run the booking application
CMD ["java", "-jar", "/app/target/quarkus-app/quarkus-run.jar"]
