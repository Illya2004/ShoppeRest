# Build stage
FROM gradle:7.2.0-jdk17 AS build
COPY . .
RUN ./gradlew clean build -x test

# Tag the image
ARG IMAGE_TAG
ARG REGISTRY_URL
ARG REMOTE_IMAGE_NAME
RUN docker tag myapp:$IMAGE_TAG $REGISTRY_URL/$REMOTE_IMAGE_NAME:$IMAGE_TAG

# Production stage
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /build/libs/SpringPr-0.0.1-SNAPSHOT.jar SpringPr.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "SpringPr.jar"]