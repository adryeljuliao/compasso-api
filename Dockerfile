#FROM openjdk:11-jdk-slim
FROM openjdk
FROM maven:latest as builder

WORKDIR /app
COPY pom.xml /app
COPY src/ /app/src
RUN mvn clean install

ENTRYPOINT ["java", "-jar", "target/desafio-0.0.1-SNAPSHOT.jar"]
