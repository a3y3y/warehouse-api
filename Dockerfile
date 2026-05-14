# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /workspace

# Copy Maven wrapper and project files
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Build the Spring Boot application
RUN ./mvnw -DskipTests package -DskipTransferProgress

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built fat jar from the builder stage
COPY --from=builder /workspace/target/warehouse-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
