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
import com.canoo.platform.core.http.HttpClient;

public class RestClient {

    public static void main(String[] args) throws Exception {
        final String endpoint = "http://localhost:8080/openid-connect";
        final HttpClient httpClient = PlatformClient.getService(HttpClient.class);

        final City city = new City("Dortmund", "Germany");

        final CityDetails details = httpClient.post(endpoint).
                withContent(city).
                readObject(CityDetails.class).
                execute().get().getContent();

        System.out.println("City " + details.getName() + " has " + details.getPopulation() + " citizens");
    }
}
