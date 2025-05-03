FROM maven:3-eclipse-temurin-24 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY

ENV AWS_REGION=us-east-1
ENV AWS_BUCKET_NAME=qrcode-gen0012

ENTRYPOINT [ "java", "-jar", "app.jar" ]