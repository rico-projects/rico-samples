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
package com.canoo.platform.samples.distribution.client.view;

import com.canoo.platform.remoting.client.ClientContext;
import com.canoo.platform.remoting.client.Param;
import com.canoo.platform.remoting.client.javafx.FXBinder;
import com.canoo.platform.remoting.client.javafx.view.AbstractFXMLViewController;
import com.canoo.platform.samples.distribution.common.model.ToDoList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import static com.canoo.platform.samples.distribution.common.DistributionAppConstants.ADD_ACTION;
import static com.canoo.platform.samples.distribution.common.DistributionAppConstants.CONTROLLER_NAME;
import static com.canoo.platform.samples.distribution.common.DistributionAppConstants.ITEM_PARAM;
import static com.canoo.platform.samples.distribution.common.DistributionAppConstants.REMOVE_ACTION;

public class ToDoView extends AbstractFXMLViewController<ToDoList> {

    @FXML
    private TextField createField;

    @FXML
    private Button createButton;

    @FXML
    private ListView<String> itemList;

    public ToDoView(ClientContext clientContext) {
        super(clientContext, CONTROLLER_NAME, ToDoView.class.getResource("view.fxml"));
    }

    @Override
    protected void init() {
        itemList.setCellFactory(c -> {
            final ListCell<String> cell = new ListCell<>();
            cell.itemProperty().addListener(e -> cell.setText(cell.getItem()));
            cell.setOnMouseClicked(e -> invoke(REMOVE_ACTION, new Param(ITEM_PARAM, cell.getItem())));
            return cell;
        });
        FXBinder.bind(createField.textProperty()).bidirectionalTo(getModel().newItemTextProperty());
        FXBinder.bind(itemList.getItems()).bidirectionalTo(getModel().getItems());
        createButton.setOnAction(event -> invoke(ADD_ACTION));
    }
}
