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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/city")
public class CityEndpoint {

    @Autowired
    private ServerTiming serverTiming;

    @RequestMapping(method = RequestMethod.GET)
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

    @RequestMapping(method = RequestMethod.POST)
    public CityDetails getDetails(@RequestBody final City city) {
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

    private void sleep(final long ms) {
        try {
            Thread.sleep(ms);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

}
