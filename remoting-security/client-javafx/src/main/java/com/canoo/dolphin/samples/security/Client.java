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

import com.canoo.platform.client.ClientConfiguration;
import com.canoo.platform.client.PlatformClient;
import com.canoo.platform.remoting.client.ClientContext;
import com.canoo.platform.remoting.client.ClientContextFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URI;


public class Client extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);
        final LoginDialog loginDialog = new LoginDialog();
        boolean login = loginDialog.showAndWait().orElse(false);
        if(login) {
            showApp(primaryStage);
        } else {
            System.exit(0);
        }
    }

    private void showApp(final Stage primaryStage) throws Exception {
        final ClientConfiguration clientConfiguration = PlatformClient.getClientConfiguration();
        final ClientContextFactory contextFactory = PlatformClient.getService(ClientContextFactory.class);
        final URI endpoint = new URI("http://localhost:8080/dolphin");
        final ClientContext clientContext = contextFactory.create(clientConfiguration, endpoint);
        clientContext.connect().handle((v, e) -> {
            final UserView view = new UserView(clientContext);
            Platform.runLater(() -> {
                primaryStage.setScene(new Scene(view.getParent()));
                primaryStage.show();
            });
            return null;
        });
    }

    public static void main(String[] args) {
        Client.launch(args);
    }
}
