module dev.rico.samples.simple.security.server.spring {

    opens dev.rico.samples.security.server;

    requires org.slf4j;
    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires dev.rico.security.server.spring;
    requires com.fasterxml.jackson.databind;
}