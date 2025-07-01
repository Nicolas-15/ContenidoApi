# Imagen final que solo ejecuta el JAR ya compilado
FROM eclipse-temurin:17-jdk AS prod
WORKDIR /app

COPY target/*.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
