# Usar una imagen base de Maven para construir el proyecto
FROM maven:3.8.6-openjdk-17 AS build

COPY target/seguimiento-egresado-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

