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
package com.canoo.dolphin.samples.security;

import com.canoo.platform.remoting.client.ClientContext;
import com.canoo.platform.remoting.client.javafx.FXBinder;
import com.canoo.platform.remoting.client.javafx.view.AbstractViewController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static com.canoo.dolphin.samples.security.Constants.USER_CONTROLLER;

public class UserView extends AbstractViewController<UserBean> {

    private final GridPane pane;

    private final TextField userField;

    private final TextField mailField;

    public UserView(final ClientContext clientContext) {
        super(clientContext, USER_CONTROLLER);
        this.pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(24));
        userField = new TextField();
        mailField = new TextField();
        pane.add(new Label("Username:"), 0, 0);
        pane.add(userField, 1, 0);
        pane.add(new Label("Mail address:"), 0, 1);
        pane.add(mailField, 1, 1);
    }

    @Override
    protected void init() {
        FXBinder.bind(userField.textProperty()).to(getModel().userNameProperty());
        FXBinder.bind(mailField.textProperty()).to(getModel().mailAddressProperty());
    }

    @Override
    public Node getRootNode() {
        return pane;
    }
}
