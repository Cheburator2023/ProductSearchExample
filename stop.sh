#!/bin/bash

# Delete Kubernetes configurations
kubectl delete -f k8s/app-deployment.yaml
kubectl delete -f k8s/postgres-deployment.yaml

# Optionally, delete the Docker image locally
docker rmi victor2023victorovich/product-search:latest