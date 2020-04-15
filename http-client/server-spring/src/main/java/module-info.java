module dev.rico.samples.http.server.spring {

    opens dev.rico.samples.http.server;

    requires dev.rico.samples.http.common;
    requires dev.rico.server.spring;
    requires spring.boot;
    requires spring.web;
    requires com.fasterxml.jackson.databind;

}