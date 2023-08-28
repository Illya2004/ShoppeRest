# Use the official Gradle image to build the project
FROM gradle:7.2.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

# Use the official OpenJDK image to run the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/SpringPr-0.0.1-SNAPSHOT.jar SpringPr.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "SpringPr.jar"]