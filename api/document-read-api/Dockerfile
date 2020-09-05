FROM openjdk:14-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ARG CONFIG=src/main/resources/application-production.yaml
COPY ${CONFIG} applictaion-production.yaml
ENTRYPOINT ["java","-jar","-Dspring.config.location=/applictaion-production.yaml","/app.jar"]