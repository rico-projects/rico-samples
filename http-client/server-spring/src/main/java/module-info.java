module dev.rico.samples.http.server.spring {

    requires dev.rico.samples.http.common;
    requires dev.rico.server.spring;
    requires spring.boot;
    requires spring.web;
    requires com.fasterxml.jackson.databind;

    opens dev.rico.samples.http.server;
}