module dev.rico.samples.simple.security.server.spring {

    exports dev.rico.samples.security.server;
    opens dev.rico.samples.security.server;

    requires org.slf4j;
    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires dev.rico.security.server.spring;
    requires dev.rico.server.spring;
    requires com.fasterxml.jackson.databind;
}