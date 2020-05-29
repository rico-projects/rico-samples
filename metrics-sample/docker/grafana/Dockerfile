FROM grafana/grafana:7.0.1
MAINTAINER Hendrik Ebbers, karakun.com
ADD prometheus-datasource.yaml /etc/grafana/provisioning/datasources/prometheus-datasource.yaml
ADD dashboards.yaml /etc/grafana/provisioning/dashboards/dashboards.yaml
ADD dashboards /var/lib/grafana/dashboards
