@echo off
REM Script to build and start the Spring Boot application in Docker

REM Build the project and Docker image
call build.bat

REM Start the application using Docker Compose
docker-compose up -d