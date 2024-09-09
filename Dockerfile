# Usa una imagen base de Maven para construir la aplicación
FROM maven:3.9.2-openjdk-17 AS build

# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias de Maven
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el resto del código fuente
COPY src ./src

# Compila la aplicación y empaqueta el archivo JAR
RUN mvn package -DskipTests

# Usa una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-alpine

# Copia el archivo JAR desde la etapa de construcción
COPY --from=build /app/target/seguimiento-egresado-0.0.1-SNAPSHOT.jar /app/seguimiento-egresado.jar

# Configura el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/seguimiento-egresado.jar"]

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 5555
