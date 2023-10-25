FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
ENTRYPOINT ["java","-jar","target/solar-watch-0.0.1-SNAPSHOT.jar"]