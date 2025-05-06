# Use Java 21 base image
FROM eclipse-temurin:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy everything from the project directory to the container
COPY . .

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Build the project without running tests
RUN ./mvnw clean install -DskipTests

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the Spring Boot application
CMD ["./mvnw", "spring-boot:run"]
