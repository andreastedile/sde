# Voting App

Clone the repository:

```
$ git clone https://github.com/andreastedile/sde
```

All the following commands must be issued from the project's root directory.

## Manual build

```
$ docker build -t dbapi db_api
$ docker build -t voteapi vote_api
$ docker build -t resultsapi results_api
```

## Manual run

```
$ docker network create voting-app-net

$ docker run \
	-e "POSTGRES_USER=postgres" \
	-e "POSTGRES_PASSWORD=password" \
	-e "POSTGRES_DB=dbvotes" \
	-v $(pwd)/db_api/init.sql:/docker-entrypoint-initdb.d/init.sql \
	--network voting-app-net \
	--hostname db \
	--name db \
	-d \
	postgres

$ docker run \
	-e "DB_HOST=db" \
	-e "DB_USER=postgres" \
	-e "DB_PASSWORD=password" \
	-e "POSTGRES_DB=dbvotes" \
	-p 5000:80 \
	--network voting-app-net \
	--hostname dbapi \
	--name dbapi \
	-d \
	dbapi
	
$ docker run \
	-e "DB_HOST=dbapi" \
	-p 7000:80 \
	--network voting-app-net \
	--hostname voteapi \
	--name voteapi \
	-d \
	voteapi

$ docker run \
	-e "DB_HOST=dbapi" \
	-p 8000:80 \
	--network voting-app-net \
	--hostname resultsapi \
	--name resultsapi \
	-d \
	resultsapi
```

## Automatic build

```
$ docker-compose build
```

## Automatic run

```
$ docker-compose up
```

