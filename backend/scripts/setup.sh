#!/usr/bin/env bash

#docker swarm init

docker pull postgres:9.6.12
docker build -t gatech/backend ../
docker build -t gatech/express-nginx ./nginx
#docker run -p 8080:8080 -t gatech/backend
#docker run -p 5432:5432 -t postgres

docker node ls
docker network create --driver overlay sad-local-network
docker network ls

docker service rm  $(docker service ls -q -f name=grocery-express-service)
docker service rm  $(docker service ls -q -f name=postgres-service)
docker service rm  $(docker service ls -q -f name=express-nginx-service)

docker service create --network sad-local-network --name postgres-service -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:9.6.12
docker service create --replicas 3 --network sad-local-network --name grocery-express-service -p 8081:8080 gatech/backend
docker service create --network sad-local-network --name express-nginx-service -p 8080:80 gatech/express-nginx

docker service ls
docker container ls

#echo "sleeping for 10 seconds..."
#for i in $(seq 1 10); do sleep 1s; echo .; done;

