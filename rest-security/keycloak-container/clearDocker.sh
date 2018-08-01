#!/bin/bash
docker rm -f $(docker ps -a -q -f "name=platform-security-sample-keycloak-server")
docker rmi -f $(docker images -q -f "reference=platform-security-sample-keycloak-server" )