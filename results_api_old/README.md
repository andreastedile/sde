# Results frontend

This frontend allows to visualize the status of the voting process.

Language: Javascript

Framework: NodeJS / express

## How to run

Note: To enjoy the functionalities of this app, first start the Database API in the correct mode.

Then:

Development mode:

```
$ export PORT=8000
$ npm run start:dev
```

Production mode:

```
$ export PORT=8000
$ NODE_ENV=production
$ npx nodemon server.js 
```

## Run with Docker

First, start a Database API container in the correct mode.

Then, start this container, which is reachable at port 80.

Development mode:

```
$ docker build \
  -f Dockerfile.dev \
  -t results_api.dev .
$ docker run \
  -p 8000:80 \
  -v $(pwd):/app \
  --name results_api.dev \
  results_api.dev
```

Some commands:

```bash
docker run \
    -p 8080:8080 \
    --mount type=bind,source="$(pwd)",target=/usr/src/app \
    --name node_dev \
    node_dev
```

