#!/usr/bin/env bash

SCENARIO=$1


## use this to ssh into pod
#docker exec -it $(docker ps -q -f name=grocery-express-service) sh
##
#curl --request GET 'localhost:8080/test'
#curl --request GET 'localhost:8081/test'

curl -s -o ../docker_results/drone_delivery_${SCENARIO}_results.txt --location --request POST 'localhost:8080/uploadTest' --form file=@"../test_scenarios/commands_${SCENARIO}.txt"
diff -s ../docker_results/drone_delivery_${SCENARIO}_results.txt ../test_results/drone_delivery_initial_${SCENARIO}_results.txt > ../docker_results/diff_results_${SCENARIO}.txt
cat ../docker_results/diff_results_${SCENARIO}.txt


