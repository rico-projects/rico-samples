module dev.rico.samples.http.server.spring {

    opens dev.rico.samples.http.server;

    requires dev.rico.server.spring;
    requires dev.rico.logback.appender;
    requires spring.boot;
    requires spring.web;
    requires spring.context;
    requires spring.beans;
    requires org.slf4j;
}
