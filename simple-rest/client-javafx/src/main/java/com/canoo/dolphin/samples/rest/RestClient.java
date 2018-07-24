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
package com.canoo.dolphin.samples.rest;

import com.canoo.platform.client.PlatformClient;
import com.canoo.platform.client.javafx.FxToolkit;
import com.canoo.platform.core.http.HttpClient;
import com.canoo.platform.core.http.RequestMethod;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RestClient extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        PlatformClient.init(new FxToolkit());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String endpoint = "http://localhost:8080/simple-rest/api/city";
        final HttpClient httpClient = PlatformClient.getService(HttpClient.class);

        final TextField cityField = new TextField();
        cityField.setPromptText("Enter city name");

        final Button button = new Button("Find details");
        button.setOnAction(e -> {
            final City city = new City(cityField.getText(), "Germany");
            httpClient.request(endpoint, RequestMethod.POST).
                    withContent(city).
                    readObject(CityDetails.class).
                    onDone(d -> showResult(d.getContent())).
                    onError(ex -> showError(city)).
                    execute();
        });

        final HBox pane = new HBox(cityField, button);
        pane.setSpacing(24);
        pane.setPadding(new Insets(24));

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public void showResult(final CityDetails details) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("City info");
        alert.setContentText("City " + details.getName() + " has " + details.getPopulation() + " citizens");
        alert.showAndWait();
    }

    public void showError(final City city) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Can not get details for city " + city.getName());
        alert.showAndWait();
    }
}
