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
package com.canoo.platform.samples.lazyloading;

import com.canoo.platform.remoting.client.ClientContext;
import com.canoo.platform.remoting.client.javafx.FXBinder;
import com.canoo.platform.remoting.client.javafx.view.AbstractViewController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;

import static com.canoo.platform.samples.lazyloading.Constants.CONTROLLER_NAME;
import static com.canoo.platform.samples.lazyloading.Constants.REFRESH_ACTION;

public class View extends AbstractViewController<LazyLoadingBean> {

    private final VBox root;

    private final ListView<LazyLoadingItem> list;

    private final Button refreshButton;

    private final ProgressIndicator progressIndicator;

    private final Spinner<Integer> sizeSpinner;

    public View(ClientContext clientContext) {
        super(clientContext, CONTROLLER_NAME);

        sizeSpinner = new Spinner<>(1, 100, 10);
        progressIndicator = new ProgressIndicator();
        refreshButton = new Button("refresh");
        refreshButton.setGraphic(progressIndicator);
        progressIndicator.setVisible(false);
        list = new ListView<>();
        root = new VBox(sizeSpinner, list, refreshButton);
        root.setSpacing(12);
        root.setPadding(new Insets(24));
    }

    @Override
    protected void init() {
        refreshButton.setOnAction(e -> invoke(REFRESH_ACTION));
        FXBinder.bind(list.getItems()).to(getModel().getItems());
        FXBinder.bind(progressIndicator.visibleProperty()).to(getModel().loadingProperty());
        FXBinder.bind(getModel().requestedSizeProperty()).to(sizeSpinner.valueProperty());
    }

    @Override
    public Node getRootNode() {
        return root;
    }
}
