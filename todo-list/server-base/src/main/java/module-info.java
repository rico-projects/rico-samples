module dev.rico.samples.todolist.server {

    opens dev.rico.samples.todo.server;
    exports dev.rico.samples.todo.server;

    requires dev.rico.samples.todolist.common;

    requires jakarta.inject.api;
    requires java.annotation;
    requires dev.rico.remoting.server;

}