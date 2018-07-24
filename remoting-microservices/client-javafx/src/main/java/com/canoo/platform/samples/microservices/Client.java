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
package com.canoo.platform.samples.microservices;

import com.canoo.platform.client.ClientConfiguration;
import com.canoo.platform.client.PlatformClient;
import com.canoo.platform.remoting.client.ClientContext;
import com.canoo.platform.remoting.client.ClientContextFactory;
import com.canoo.platform.remoting.client.javafx.FXBinder;
import com.canoo.platform.samples.microservices.product.ProductBean;
import com.canoo.platform.samples.microservices.user.UserBean;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.canoo.platform.samples.microservices.product.ProductConstants.PRODUCT_CONTROLLER_NAME;
import static com.canoo.platform.samples.microservices.product.ProductConstants.PRODUCT_REFRESH_ACTION;
import static com.canoo.platform.samples.microservices.user.UserConstants.USER_CONTROLLER_NAME;
import static com.canoo.platform.samples.microservices.user.UserConstants.USER_REFRESH_ACTION;

public class Client extends Application {

    private ClientContext userContext;

    private ClientContext productContext;

    @Override
    public void init() throws Exception {
        final URI productUrl = new URI("http://localhost:8080/dolphin");
        productContext = createClientContext(productUrl);
        productContext.connect().get(3_000, TimeUnit.MILLISECONDS);

        final URI userUrl = new URI("http://localhost:8081/dolphin");
        userContext = createClientContext(userUrl);
        userContext.connect().get(3_000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final TextField productNameField = new TextField();
        productNameField.setEditable(false);
        final TextField productPriceField = new TextField();
        productPriceField.setEditable(false);
        final Button productRefreshButton = new Button("refresh");
        productRefreshButton.setDisable(true);
        final VBox productBox = new VBox(productNameField, productPriceField, productRefreshButton);
        productBox.setSpacing(6);
        productContext.<ProductBean>createController(PRODUCT_CONTROLLER_NAME).handle((c, e) -> {
            FXBinder.bind(productNameField.textProperty()).to(c.getModel().nameProperty());
            FXBinder.bind(productPriceField.textProperty()).to(c.getModel().priceProperty(), d -> Optional.ofNullable(d).map(v -> v.toString()).orElse(""));
            productRefreshButton.setDisable(false);
            productRefreshButton.setOnAction(ev -> c.invoke(PRODUCT_REFRESH_ACTION));
            return null;
        });

        final TextField userNameField = new TextField();
        userNameField.setEditable(false);
        final Button userRefreshButton = new Button("refresh");
        userRefreshButton.setDisable(true);
        final VBox userBox = new VBox(userNameField, userRefreshButton);
        userBox.setSpacing(6);
        userContext.<UserBean>createController(USER_CONTROLLER_NAME).handle((c, e) -> {
            FXBinder.bind(userNameField.textProperty()).to(c.getModel().nameProperty());
            userRefreshButton.setDisable(false);
            userRefreshButton.setOnAction(ev -> c.invoke(USER_REFRESH_ACTION));
            return null;
        });

        final HBox mainBox = new HBox(productBox, userBox);
        mainBox.setSpacing(12);
        mainBox.setPadding(new Insets(24));

        primaryStage.setScene(new Scene(mainBox));
        primaryStage.show();
    }

    private final ClientContext createClientContext(final URI endpoint) throws Exception {
        final ClientConfiguration clientConfiguration = PlatformClient.getClientConfiguration();
        final ClientContextFactory clientContextFactory = PlatformClient.getService(ClientContextFactory.class);
        return clientContextFactory.create(clientConfiguration, endpoint);
    }

    public static void main(String[] args) {
        launch();
    }
}
