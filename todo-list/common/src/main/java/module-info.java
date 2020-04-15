module dev.rico.samples.todolist.common {

    opens dev.rico.samples.todo;
    exports dev.rico.samples.todo;

    requires dev.rico.remoting.common;
}