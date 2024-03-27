FROM eclipse-temurin:17-jdk-alpine

COPY target/contact-management-0.0.1-SNAPSHOT.jar /app/contactmanagement.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT exec java -jar -Dspring.profiles.active=docker contactmanagement.jar