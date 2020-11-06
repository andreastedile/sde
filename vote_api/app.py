from os import environ
from socket import gethostname
from flask import Flask, request, make_response, jsonify, render_template
from requests import post, RequestException

DB_HOST = environ.get('DB_HOST', '172.16.238.10')
DB_PORT = environ.get('DB_PORT', '5000')

app = Flask(__name__)


@app.route('/')
def home():
    print('/')

    return render_template(
        'index.html',
        hostname=gethostname()
    )


@app.route('/info')
def info():
    print('/info')

    return make_response({
        'server hostname': gethostname(),
        'server port': 7000,
        'remote address': request.remote_addr
    })


@app.route('/votes', methods=['POST'])
def votes():
    print('/votes')

    body = request.json
    try:
        their_resp = post(f'http://{DB_HOST}:{DB_PORT}/votes', json=body)
        our_resp = make_response(their_resp.text, their_resp.status_code)
        print('POST to DB_API ok')
    except RequestException as e:
        print('POST to DB_API exception', e)
        json = jsonify({'header': {'status_code': 500, 'status': 'POST to DB_API exception', 'description': str(e)}})
        our_resp = make_response(json, 500)

    our_resp.mimetype = 'application/json'
    return our_resp
