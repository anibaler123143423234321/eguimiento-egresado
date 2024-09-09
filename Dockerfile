# Usa una imagen base de Maven con JDK
FROM maven:3.8.6-openjdk-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente y construye la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Usa una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR desde la etapa de construcción
COPY --from=build /app/target/seguimiento-egresado-0.0.1-SNAPSHOT.jar ./app.jar

# Expone el puerto en el que la aplicación escucha
EXPOSE 5555

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
