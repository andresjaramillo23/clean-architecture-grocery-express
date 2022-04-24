## Running Locally with Linux/Unix
Before running any of the scripts, navigate to the `backend/scripts` directory and make sure they have the appropriate permissions to be executed in your terminal:
```
chmod 777 batch.sh
chmod 777 cleanup.sh
chmod 777 setup.sh
chmod 777 test.sh
chmod 777 view_pods.sh
```
To set up the docker swarm environment run:
```
./setup.sh 
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
./batch.sh 
```
The results of the comparison will be located in the `docker_results` directory.

Once testing is complete, scale back down to 1 replica using:


### Testing single scenarios
To test specific scenarios, run the command (01 can be replaced with desired scenario):
```
./test.sh 01
```

## Scaling
To scale up backend server pods to 3 replicas, run:
```
docker service scale grocery-express-service=3
```

This command allows you to see the ip addresses of the backend pods running in the docker swarm environment:
```
./view_pods.sh
```

You can also see the load balancer in action by visiting `localhost:8081/test`.
This will return the pod's id address as the response body.
If you refresh that page, you can see the ip address change between the different pods.

## Clean up 
This command stops and removes the docker swarm services along with the unused docker containers.
```
./cleanup.sh
```