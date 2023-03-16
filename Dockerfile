FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM adoptopenjdk:11-jre-hotspot-bionic
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]