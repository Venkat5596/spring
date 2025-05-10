FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

COPY target/SpringBootProject.war app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.war"]
