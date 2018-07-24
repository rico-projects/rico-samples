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
package com.canoo.platform.samples.distribution.client;

import com.canoo.platform.client.PlatformClient;
import com.canoo.platform.client.javafx.FxToolkit;
import com.canoo.platform.remoting.client.ClientContext;
import com.canoo.platform.remoting.client.ClientContextFactory;
import com.canoo.platform.samples.distribution.client.view.ToDoView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;

public class DistributionClient extends Application {

    private void connect(final Stage primaryStage, final URI endpoint) {
        final ClientContextFactory clientContextFactory = PlatformClient.getService(ClientContextFactory.class);


        final ClientContext clientContext = clientContextFactory.create(PlatformClient.getClientConfiguration(), endpoint);
        clientContext.connect().handle((v, e) -> {
            if (e != null) {
                e.printStackTrace();
                System.exit(-1);
            }
            Platform.runLater(() -> {
                try {
                    ToDoView viewController = new ToDoView(clientContext);
                    Scene scene = new Scene(viewController.getParent());
                    scene.getStylesheets().add(DistributionClient.class.getResource("style.css").toExternalForm());
                    primaryStage.setScene(scene);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    System.exit(-1);
                }
            });
            return null;
        });
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        FxToolkit.init();

        final Button server1Button = new Button("Server instance 1");
        final URI server1Url = new URI("http://localhost:8082/distribution-app/dolphin");
        server1Button.setOnAction(e -> connect(primaryStage, server1Url));

        final Button server2Button = new Button("Server instance 2");
        final URI server2Url = new URI("http://localhost:8083/distribution-app/dolphin");
        server2Button.setOnAction(e -> connect(primaryStage, server2Url));

        final VBox selectBox = new VBox(server1Button, server2Button);
        selectBox.setPadding(new Insets(24));
        selectBox.setSpacing(24);

        Scene scene = new Scene(selectBox);
        scene.getStylesheets().add(DistributionClient.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
