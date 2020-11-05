from socket import gethostname
from flask import Flask, request, make_response, jsonify, render_template
from requests import post, RequestException

DB_API = 'http://172.16.238.10:5000/votes'

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
        'server port': 8000,
        'remote address': request.remote_addr
    })


@app.route('/votes', methods=['POST'])
def votes():
    print('/votes')

    body = request.json
    try:
        their_resp = post(DB_API, json=body)
        our_resp = make_response(their_resp.text, their_resp.status_code)
        print('POST to DB_API ok')
    except RequestException:
        print('POST to DB_API exception')
        json = jsonify({'header': {'status_code': 500, 'status': 'POST to DB_API exception'}})
        our_resp = make_response(json, 200)

    our_resp.mimetype = 'application/json'
    return our_resp
