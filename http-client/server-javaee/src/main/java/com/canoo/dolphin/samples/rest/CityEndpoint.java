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

import com.canoo.platform.server.timing.Metric;
import com.canoo.platform.server.timing.ServerTiming;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("city")
public class CityEndpoint {

    @Inject
    private ServerTiming serverTiming;

    @GET
    public String testMetrics() {
        final Metric metric1 = serverTiming.start("loadCache");
        sleep(1_100);
        metric1.stop();

        final Metric metric2 = serverTiming.start("loadFromDB");
        sleep(456);
        metric2.stop();

        final Metric metric3 = serverTiming.start("convertData");
        sleep(1_462);
        metric3.stop();

        return "DONE";
    }

    @POST
    public CityDetails getDetails(final City city) {
        final Metric metric = serverTiming.start("getCityDetail");
        try {
            final CityDetails cityDetails = new CityDetails(city);
            cityDetails.setDescription("No description");
            cityDetails.setPopulation((long) (Math.random() * 1_000_000));
            return cityDetails;
        } finally {
            metric.stop();
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
