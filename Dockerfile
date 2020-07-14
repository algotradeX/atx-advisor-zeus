FROM openjdk:11-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
RUN echo ${RUNTIME_ENV}

COPY ./entrypoint.sh /
ENTRYPOINT ["./entrypoint.sh"]
