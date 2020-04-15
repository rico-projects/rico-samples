module dev.rico.samples.todolist.common {
    exports dev.rico.samples.todo;
    opens dev.rico.samples.todo;

    requires dev.rico.remoting.common;
}