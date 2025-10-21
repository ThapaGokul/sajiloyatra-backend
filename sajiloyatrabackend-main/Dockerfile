# Stage 1: Build the application
# Use a Maven-capable image that includes Java 17
FROM maven:3.8-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml first
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Copy the source code
COPY src ./src

# Make the mvnw script executable and build the .jar
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

# Stage 2: Create the final, lightweight runtime image
FROM openjdk:17-slim-bullseye

WORKDIR /app

# Copy the .jar file from the 'build' stage
# This wildcard copies the .jar and avoids name mismatches
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","/app.jar"]