FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/SpringBootProject.war app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.war","--server.port=${PORT}"]
