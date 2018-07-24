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
package com.canoo.dolphin.todo.client;

import com.canoo.platform.remoting.client.ClientContext;
import com.canoo.platform.remoting.client.javafx.FXBinder;
import com.canoo.platform.remoting.client.javafx.view.AbstractFXMLViewController;
import com.canoo.platform.samples.distribution.common.model.ToDoItem;
import com.canoo.platform.samples.distribution.common.model.ToDoList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import static com.canoo.dolphin.todo.TodoAppConstants.ADD_ACTION;
import static com.canoo.dolphin.todo.TodoAppConstants.TODO_CONTROLLER_NAME;

public class ToDoView extends AbstractFXMLViewController<ToDoList> {

    @FXML
    private TextField createField;

    @FXML
    private Button createButton;

    @FXML
    private ListView<ToDoItem> itemList;

    public ToDoView(ClientContext clientContext) {
        super(clientContext, TODO_CONTROLLER_NAME, ToDoView.class.getResource("view.fxml"));
    }

    @Override
    protected void init() {
        itemList.setCellFactory(c -> new ToDoItemCell(getControllerProxy()));
        FXBinder.bind(createField.textProperty()).bidirectionalTo(getModel().getNewItemText());
        FXBinder.bind(itemList.getItems()).bidirectionalTo(getModel().getItems());
        createButton.setOnAction(event -> invoke(ADD_ACTION));
    }
}
