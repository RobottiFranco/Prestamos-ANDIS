FROM openjdk:17-jdk-slim

COPY target/prestamos-0.0.1-SNAPSHOT.jar /app/prestamos.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/prestamos.jar"]
