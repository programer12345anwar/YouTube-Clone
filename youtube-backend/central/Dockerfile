# === First stage: build the JAR with Maven ===
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# === Second stage: run the JAR with JDK ===
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/central-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]