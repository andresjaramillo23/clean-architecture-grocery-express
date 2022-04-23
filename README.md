## Running Locally
Before running any of the scripts, make sure they have the appropriate permissions to be executed in your terminal:
```
chmod 777 backend/scripts/batch.sh
chmod 777 backend/scripts/cleanup.sh
chmod 777 backend/scripts/setup.sh
chmod 777 backend/scripts/test.sh
chmod 777 backend/scripts/view_pods.sh
```
To set up the docker swarm environment run:
```
./backend/scripts/setup.sh 
```
This script will set up:
- A docker swarm network ingress 
- The grocery-express service on localhost:8081 with 3 replicas
- The postgres service on localhost:5432
- An nginx server to load balance all requests from localhost:80 to the grocery-express services


## Testing
### Testing all files in batch 
In order to test all command files within the `test_scenarios` directory and compare them to the 
expected results defined in the `test_results` directory,
run the command:
```
./backend/scripts/batch.sh 
```
The results of the comparison will be located in the `docker_results` directory.

### Testing single scenarios
To test specific scenarios, run the command (01 can be replaced with desired scenario):
```
./backend/scripts/test.sh 01
```

## View backend pod's ip addresses
This command allows you to see the ip addresses of the backend pods running in the docker swarm environment: 
```
./backend/scripts/view_pods.sh
```

## Clean up 
This command stops and removes the docker swarm services along with the unused docker containers.
```
./backend/scripts/cleanup.sh
```