FROM gradle:7.2.0-jdk17 AS build
COPY . .
RUN gradle build -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /build/libs/SpringPr-0.0.1-SNAPSHOT.jar SpringPr.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "SpringPr.jar"]