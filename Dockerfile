#Imagen de java
FROM openjdk:17-jdk-slim
WORKDIR /app
#Copia el archivo de la snapshot generada
COPY target/ms-movements-0.0.1-SNAPSHOT.jar app.jar
#Puerto expuesto
EXPOSE 8080
#Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
