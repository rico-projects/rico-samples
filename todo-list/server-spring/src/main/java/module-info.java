module dev.rico.samples.todolist.server.spring {

    requires dev.rico.samples.todolist.server;
    requires dev.rico.remoting.server.spring;
    requires dev.rico.remoting.server;
    requires dev.rico.server.spring;

    requires spring.beans;
    requires spring.context;
    requires spring.boot;
}