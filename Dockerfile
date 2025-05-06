# Use Eclipse Temurin 21 as build image
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy all project files
COPY . .

# Give permission and run Maven to build the app without tests
RUN chmod +x ./mvnw && ./mvnw -DskipTests clean package

# Use Eclipse Temurin 21 as runtime image
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/recipeapi-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]

