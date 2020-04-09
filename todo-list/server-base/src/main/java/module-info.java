module dev.rico.samples.todolist.server {
    exports dev.rico.samples.todo.server;

    requires dev.rico.samples.todolist.common;

    requires javax.inject;
    requires java.annotation;

    requires dev.rico.core;
    requires dev.rico.remoting.server;
    requires dev.rico.remoting.common;
}