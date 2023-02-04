FROM openjdk:8-jdk-alpine
RUN mkdir app
MAINTAINER baeldung.com
COPY target/Risk-Engine-0.0.1.jar risk-engine-0.0.1.jar
ENTRYPOINT ["java","-jar","/risk-engine-0.0.1.jar"]

