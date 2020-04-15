package dev.rico.samples.http.client;

import dev.rico.client.Client;
import dev.rico.core.http.HttpClient;
import dev.rico.samples.http.common.City;
import dev.rico.samples.http.common.CityDetails;

public class RestClient {

    public static void main(String[] args) throws Exception {
        final String endpoint = "http://localhost:8080/simple-rest/api/city";
        final HttpClient httpClient = Client.getService(HttpClient.class);

        final City city = new City("Dortmund", "Germany");

        final CityDetails details = httpClient.post(endpoint).
                withContent(city).
                readObject(CityDetails.class).
                execute().get().getContent();

        System.out.println("City " + details.getName() + " has " + details.getPopulation() + " citizens");
    }
}
