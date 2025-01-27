#!/bin/bash

# Set Docker Hub username
DOCKER_HUB_USERNAME=victor2023victorovich

# Build the Docker image
docker build -t $DOCKER_HUB_USERNAME/product-search:latest .

# Push the Docker image to Docker Hub
docker push $DOCKER_HUB_USERNAME/product-search:latest

# Apply Kubernetes configurations
kubectl apply -f k8s/postgres-deployment.yaml
kubectl apply -f k8s/app-deployment.yaml