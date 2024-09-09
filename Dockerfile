# Usar una imagen base de Maven para construir el proyecto
FROM amazoncorretto:21

COPY target/seguimiento-egresado-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

