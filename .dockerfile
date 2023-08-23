# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /src

# Copy the executable JAR file from the target directory into the container at /app
COPY target/SpringPr.jar .

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file when the container launches
CMD ["java", "-jar", "SpringPr.jar"]