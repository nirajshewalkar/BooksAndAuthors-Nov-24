FROM openjdk:21-jdk
MAINTAINER niraj.com
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]