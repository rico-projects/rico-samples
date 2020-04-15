module dev.rico.samples.todolist.server.spring {

    requires dev.rico.samples.todolist.server;
    requires dev.rico.remoting.server.spring;

    requires spring.beans;
    requires spring.context;
    requires spring.boot;

    opens dev.rico.samples.todo.server.spring;
    exports dev.rico.samples.todo.server.spring;
}