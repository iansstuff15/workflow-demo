FROM --platform=linux/x86_64 openjdk:17-jdk-alpine
LABEL authors="dirian"

ARG JAR_FILE=target/*.jar
COPY ./target/workflow-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]