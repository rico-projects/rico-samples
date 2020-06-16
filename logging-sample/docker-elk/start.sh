#!/bin/bash
docker-compose rm -f -s
docker-compose build
docker-compose up
