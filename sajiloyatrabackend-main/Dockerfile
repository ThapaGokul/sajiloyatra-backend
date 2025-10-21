# Use a standard Java 17 base image
FROM openjdk:17-slim

# Set a variable for the JAR file
# Make sure your .jar file name matches this!
# Check your /target folder to confirm.
ARG JAR_FILE=target/SajiloyatraImplementation-0.0.1-SNAPSHOT.jar

# Copy the built .jar file into the container
COPY ${JAR_FILE} app.jar

# Tell the container to run the .jar file
ENTRYPOINT ["java","-jar","/app.jar"]