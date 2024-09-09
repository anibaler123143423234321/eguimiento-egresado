# Usar una imagen base de Maven para construir el proyecto
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src /app/src
RUN mvn clean package -DskipTests

# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/seguimiento-egresado-0.0.1-SNAPSHOT.jar /app/seguimiento-egresado.jar
ENTRYPOINT ["java", "-jar", "seguimiento-egresado.jar"]
