# Use a basic Maven image for building the Quarkus app
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /usr/src/app
COPY . .

# Clean and build the Quarkus app in JVM mode using standard Maven
RUN mvn clean package -Dquarkus.package.type=fast-jar

# Use a basic OpenJDK image to run the Quarkus app in development mode
FROM openjdk:17-jdk-slim
WORKDIR /work/
COPY --from=build /usr/src/app/target/quarkus-app/ /work/

# Use Quarkus dev mode for live reload
CMD ["java", "-Dquarkus.live-reload", "-jar", "/work/quarkus-run.jar"]
