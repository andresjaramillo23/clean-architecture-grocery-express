# use this to view pod ip addresses:
for x in $(seq 1 3)
do
  echo "Pod $x IP Address:" ; curl --request GET 'localhost:8080/test'; echo
done