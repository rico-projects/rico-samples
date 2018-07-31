/*
 * Copyright 2015-2018 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.rico.samples.todo.client;

import dev.rico.samples.todo.ToDoItem;
import dev.rico.samples.todo.ToDoList;
import dev.rico.client.remoting.ControllerProxy;
import dev.rico.client.remoting.FXBinder;
import dev.rico.client.remoting.Param;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Optional;

import static dev.rico.samples.todo.TodoAppConstants.CHANGE_ACTION;
import static dev.rico.samples.todo.TodoAppConstants.ITEM_PARAM;
import static dev.rico.samples.todo.TodoAppConstants.REMOVE_ACTION;
import static javafx.scene.layout.Priority.ALWAYS;
import static javafx.scene.layout.Priority.NEVER;

public class ToDoItemCell extends ListCell<ToDoItem> {

    private final ControllerProxy<ToDoList> controllerProxy;

    public ToDoItemCell(ControllerProxy<ToDoList> controllerProxy) {
        this.controllerProxy = controllerProxy;

        getStyleClass().add("todo-item-cell");
        HBox layout = new HBox();
        layout.visibleProperty().bind(emptyProperty().not());
        setGraphic(layout);

        Text itemNameText = new Text();
        itemNameText.getStyleClass().add("name-text");
        HBox.setHgrow(itemNameText, NEVER);
        layout.getChildren().add(itemNameText);

        Label spacer = new Label();
        spacer.setMaxWidth(Double.MAX_VALUE - 1);
        HBox.setHgrow(spacer, ALWAYS);
        layout.getChildren().add(spacer);

        Button deleteButton = new Button("delete");
        deleteButton.setOnAction(e -> item().ifPresent(i -> controllerProxy.invoke(REMOVE_ACTION, new Param(ITEM_PARAM, i.getText()))));
        HBox.setHgrow(deleteButton, NEVER);
        layout.getChildren().add(deleteButton);

        itemProperty().addListener((obs, oldVal, newVal) -> {
            itemNameText.textProperty().unbind();
            itemNameText.strikethroughProperty().unbind();
            if(newVal != null) {
                FXBinder.bind(itemNameText.textProperty()).to(newVal.getTextProperty());
                FXBinder.bind(itemNameText.strikethroughProperty()).bidirectionalTo(newVal.getCompletedProperty());
            }
        });

        setOnMouseClicked(e -> {
            item().ifPresent(i -> controllerProxy.invoke(CHANGE_ACTION, new Param(ITEM_PARAM, i.getText())));
        });
    }

    private Optional<ToDoItem> item() {
        return Optional.ofNullable(getItem());
    }

}
