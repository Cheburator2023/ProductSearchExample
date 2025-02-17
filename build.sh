#!/bin/bash
# Script to build the Spring Boot application and Docker image

# Clean and build the project, excluding tests
./gradlew clean build -x test

# Build the Docker image with the tag 'productsearch:latest'
docker build -t productsearch:latest .