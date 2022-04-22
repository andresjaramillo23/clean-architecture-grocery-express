docker service rm  $(docker service ls -q -f name=grocery-express-service)
docker service rm  $(docker service ls -q -f name=postgres-service)
docker service rm  $(docker service ls -q -f name=express-nginx-service)

yes | docker system prune