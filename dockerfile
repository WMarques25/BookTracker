# Usar a imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven/Gradle para o contêiner
COPY target/*.jar app.jar

# Informar o comando a ser executado quando o contêiner for iniciado
ENTRYPOINT ["java", "-jar", "app.jar"]
