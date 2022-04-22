#!/usr/bin/env bash

SCENARIO=$1

#docker build -t gatech/backend2 .
#./scripts/test.sh 00

mkdir -p ./docker_results

## working
#docker run --network sad-local-network --name dronedelivery gatech/backend2 sh -c "\
#    java -jar GroceryExpress-0.0.1-SNAPSHOT.jar < commands_${SCENARIO}.txt > drone_delivery_${SCENARIO}_results.txt && \
#    diff -s drone_delivery_${SCENARIO}_results.txt drone_delivery_initial_${SCENARIO}_results.txt > diff_results_${SCENARIO}.txt && \
#    cat diff_results_${SCENARIO}.txt ; ls ; pwd"

docker exec -it grocery-express-service.1.f3hujopsvzvt30odd3qakhxgw "\
    java -jar /GroceryExpress-0.0.1-SNAPSHOT.jar < commands_00.txt > drone_delivery_00_results.txt && \
    diff -s drone_delivery_00_results.txt drone_delivery_initial_00_results.txt > diff_results_00.txt && \
    cat diff_results_00.txt ; ls ; pwd"

#docker exec -it $(docker ps -q -f name=grocery-express-service) sh
curl --request GET 'localhost:8080/process'

#docker cp dronedelivery:/usr/src/cs6310/drone_delivery_00_results.txt ./docker_results/
docker cp dronedelivery:/usr/src/cs6310/drone_delivery_${SCENARIO}_results.txt ./docker_results/
docker cp dronedelivery:/usr/src/cs6310/diff_results_${SCENARIO}.txt ./docker_results
docker rm dronedelivery > /dev/null

