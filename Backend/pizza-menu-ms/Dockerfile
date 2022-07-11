#
# Build stage
#
FROM maven:3.8.6-openjdk-8-slim AS build
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package


#
# Package stage
#
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=/usr/src/app/target/*.jar
COPY --from=build ${JAR_FILE} /usr/app/app.jar
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]