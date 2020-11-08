FROM python:3.8.6-buster

WORKDIR /app

ADD requirements.txt /app/requirements.txt

RUN pip3 install -r requirements.txt

ADD . /app

ENV FLASK_ENV=development

ENV PYTHONUNBUFFERED=1

EXPOSE 80

CMD ["flask", "run", "--host", "0.0.0.0", "--port", "80"]
