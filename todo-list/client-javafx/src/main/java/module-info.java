module todo.list.client.javafx {
    exports dev.rico.samples.todo.client;
    opens dev.rico.samples.todo.client;


    requires dev.rico.samples.todolist.common;
    requires dev.rico.remoting.client.javafx;
    requires javafx.fxml;
    requires javafx.controls;
}