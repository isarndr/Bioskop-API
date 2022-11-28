FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} challenge6.jar
ENTRYPOINT ["java", "-jar", "/challenge6.jar"]