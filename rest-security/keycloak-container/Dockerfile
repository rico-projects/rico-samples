FROM jboss/keycloak
MAINTAINER dev.karakun.com

COPY default-realm.json /tmp/

ENV KEYCLOAK_IMPORT=/tmp/default-realm.json
ENV KEYCLOAK_USER=username
ENV KEYCLOAK_PASSWORD=password

EXPOSE 8080 9990
