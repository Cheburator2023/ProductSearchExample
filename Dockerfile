# Stage 1: Build the backend application
FROM gradle:8.12.0-jdk21 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Build the backend application
RUN ./gradlew clean build -x test

# Stage 2: Build the frontend
FROM node:16 AS frontend-build

# Set the working directory
WORKDIR /frontend

# Copy the frontend package files
COPY src/main/frontend/package.json src/main/frontend/package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the frontend source code
COPY src/main/frontend ./

# Build the frontend application
RUN npm run build

# Stage 3: Create the runtime image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Copy the built frontend files from the frontend build stage
COPY --from=frontend-build /frontend/dist /app/public

# Copy the changelog file
COPY src/main/resources/db/changelog/db.changelog-1.0.xml /app/db/changelog/db.changelog-1.0.xml

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]