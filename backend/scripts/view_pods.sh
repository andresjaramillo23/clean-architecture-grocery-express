# use this to view pod ip addresses:

declare -a arr=($(docker container ls -q -f name=grocery-express-service))

for x in "${arr[@]}"
do
  echo "Pod $x IP Address:" ; curl --request GET 'localhost:8080/test'; echo
done