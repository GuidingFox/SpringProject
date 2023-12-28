# Use a base image with Java and Maven pre-installed
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use a lightweight base image for the application runtime
FROM adoptopenjdk:17-jre-hotspot AS runtime

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built in the previous stage into the container
COPY --from=build /app/target/*.jar app.jar

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
