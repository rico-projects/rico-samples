module dev.rico.samples.http.server.spring {

    opens dev.rico.samples.http.server;

    requires dev.rico.metrics.server.spring;
    requires dev.rico.server.spring;
    requires spring.boot;
    requires spring.web;
    requires spring.beans;
    requires com.fasterxml.jackson.databind;

}
