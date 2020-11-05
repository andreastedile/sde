FROM python:3.8.6-buster

WORKDIR /app

ADD requirements.txt /app/requirements.txt

RUN pip3 install -r requirements.txt

ADD . /app

ENV FLASK_ENV=development

EXPOSE 80

CMD ["flask", "run", "--host", "0.0.0.0", "--port", "7000"]