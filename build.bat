@echo off
REM Script to build the Spring Boot application and Docker image

REM Clean and build the project, excluding tests
.\gradlew clean build -x test

REM Build the Docker image with the tag 'productsearch:latest'
docker build -t productsearch:latest .
pause