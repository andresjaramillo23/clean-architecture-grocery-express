## Running Locally
First make sure postgres docker image exists on you machine and is running:
```
docker pull postgres
docker run --name local-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres 
```
Then run app.

