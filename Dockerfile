# Etapa 1: Build da aplicação
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copia o código para dentro do container
COPY . .

# Garante permissão de execução ao mvnw
RUN chmod +x mvnw

# Compila o projeto e gera o jar
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o jar da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
