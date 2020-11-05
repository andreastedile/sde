# Vote frontend

This frontend allows to vote between cats and dogs.

Language: Python 3.8

Framework: [Flask](https://flask.palletsprojects.com/en/1.1.x/)

## How to run

[Development mode](https://flask.palletsprojects.com/en/1.1.x/server/):

```
$ export export FLASK_ENV=development
$ flask run
```

This enables the development environment, including the interactive debugger and reloader, and then starts the server on _http://localhost:5000/_.

[Externally visible server](https://flask.palletsprojects.com/en/1.1.x/quickstart/):

```
$ flask run --host=0.0.0.0
```

## Run with Docker

The Docker container is reachable at port 80, which, by default, is reserved.  

Development mode:

```
$ docker build -f Dockerfile.dev -t vote_api.dev .
$ docker run \
  -p 7000:80 \
  -v $(pwd):/app \
  --name vote_api.dev \
  vote_api.dev
```

Production mode:

```
$ docker build -t vote_api.prod -f Dockerfile.prod .
$ docker run -p 6000:80 --name vote_api.prod vote_api.prod
```

## Example

Start in development mode:

```
 * Environment: development
 * Debug mode: on
 * Running on http://0.0.0.0:80/ (Press CTRL+C to quit)
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 179-641-299
```

Make a change to app.py:

```
 * Detected change in '/app/app.py', reloading
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 179-641-299
```

Note: [read about watching extra files with the reloader](https://flask.palletsprojects.com/en/1.1.x/cli/).
