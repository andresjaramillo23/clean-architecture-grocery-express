#!/usr/bin/env bash

#docker build -t gatech/dronedelivery -f Dockerfile ../
docker build -t gatech/backend .
docker build -t gatech/express-nginx ./nginx
#docker run -p 8080:8080 -t gatech/backend

docker node ls
docker network create --driver overlay sad-local-network
docker network ls

docker pull postgres:9.6.12
docker service create --network sad-local-network --name postgres-service -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:9.6.12
docker service ls
docker container ls

# TODO: Add replicas
docker service create --network sad-local-network --name grocery-express-service -p 8080:8080 gatech/backend
docker service ls
docker container ls

docker service create --network sad-local-network --name express-nginx-service -p 8090:80 gatech/express-nginx
docker service ls
docker container ls