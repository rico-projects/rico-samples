#!/bin/bash
docker rm -f $(docker ps -a -q -f "name=rico-security-sample-keycloak-server")
docker rmi -f $(docker images -q -f "reference=rico-security-sample-keycloak-server" )