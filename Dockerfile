#FROM openjdk:17-jdk-alpine
#
#WORKDIR /app
#
#COPY pom.xml .
#COPY src ./src
#
#RUN mvn clean package -DskipTests
#
#COPY target/SpringBootProject.war app.war
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "app.war","--server.port=${PORT}"]


# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight image to run the application
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/SpringBootProject.war app.war

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.war --server.port=${PORT}"]

