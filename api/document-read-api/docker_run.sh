#!/bin/bash

docker stop api
docker rm api

mode=$1;

if [ "$mode" == 'd' ] || [ "$mode" == 'daemon' ];then
  docker run --name api -d -p 3000:8080 jachwirus/document-read-api:latest
else
  docker run --name api -p 3000:8080 jachwirus/document-read-api:latest
fi