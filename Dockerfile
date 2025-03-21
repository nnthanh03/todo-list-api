FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/todo-list-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
