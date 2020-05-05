package dev.rico.samples.http.client;

import dev.rico.client.Client;
import dev.rico.client.javafx.FxToolkit;
import dev.rico.core.http.HttpClient;
import dev.rico.core.http.RequestMethod;
import dev.rico.samples.http.common.City;
import dev.rico.samples.http.common.CityDetails;
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
        Client.init(new FxToolkit());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String endpoint = "http://localhost:8080/simple-rest/api/city";
        final HttpClient httpClient = Client.getService(HttpClient.class);

        final TextField cityField = new TextField();
        cityField.setPromptText("Enter city name");

        final Button button = new Button("Find details");
        button.setOnAction(e -> {
            final City city = new City(cityField.getText(), "Germany");
            httpClient.request(endpoint, RequestMethod.POST).
                    withContent(city).
                    readObject(CityDetails.class).
                    onDone(d -> showResult(d.getContent())).
                    onError(ex -> showError(city, ex)).
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

    public void showError(final City city, final Exception exception) {
        exception.printStackTrace();
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Can not get details for city " + city.getName());
        alert.showAndWait();
    }
}
