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
package com.canoo.platform.samples.distribution.server.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class HazelcastServer {

    @Value("${dolphinPlatform.hazelcast.group.name}")
    private String hazelcastGroup;

    @Value("${dolphinPlatform.hazelcast.server.port}")
    private int hazelcastPort;

    private HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void postConstruct() {
        Config config = new Config();
        config.getNetworkConfig().setPort(hazelcastPort);
        config.getNetworkConfig().setPortAutoIncrement(false);
        config.getGroupConfig().setName(hazelcastGroup);
        config.getManagementCenterConfig().setEnabled(false);
        hazelcastInstance = Hazelcast.newHazelcastInstance(config);
  }

    @PreDestroy
    private void destroy() {
        if(hazelcastInstance != null) {
            hazelcastInstance.shutdown();
        }
    }


    public static void main(String... args) {
        SpringApplication.run(HazelcastServer.class, args);
    }

}
