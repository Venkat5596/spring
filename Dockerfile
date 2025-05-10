FROM openjdk:17-jdk-alpine
#MAINTAINER sks.com

WORKDIR /app

COPY target/SpringBootProject-0.0.1-SNAPSHOT.jar my_app.jar

EXPOSE 8080

ENTRYPOINT ["java" , "-jar" , "my_app.jar"]