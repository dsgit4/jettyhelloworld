FROM maven:3.6.0-jdk-11-slim as target
WORKDIR /target
COPY pom.xml .
RUN mvn dependency:go-offline