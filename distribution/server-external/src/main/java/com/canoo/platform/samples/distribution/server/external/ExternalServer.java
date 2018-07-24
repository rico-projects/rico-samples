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
package com.canoo.platform.samples.distribution.server.external;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ExternalServer {

    public static void main(String[] args) {
        SpringApplication.run(ExternalServer.class);
    }

    @PostConstruct
    public void init() {
        final HazelcastInstance hazelcastInstance = createHazelcastInstance();
        final Gson gson = createGson();
        final String topicName = "item_added";
        addConsumer(hazelcastInstance, topicName);

        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                try {
                    Thread.sleep(5_000);
                    publish(hazelcastInstance, gson, topicName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addConsumer(HazelcastInstance instance, String topicName) {
        final ITopic<CustomEventFormat> topic = instance.getTopic(topicName);
        topic.addMessageListener(new MessageListener<CustomEventFormat>() {

            @Override
            public void onMessage(Message<CustomEventFormat> message) {
                System.out.println("Received: " + message.getMessageObject().getMyMessage());
            }
        });
    }

    private void publish(HazelcastInstance instance, Gson gson, String topicName) throws IOException {
        final ITopic<CustomEventFormat> topic = instance.getTopic(topicName);
        topic.publish(new CustomEventFormat("Message from external JSON publisher", topicName));
    }

    private Gson createGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    private HazelcastInstance createHazelcastInstance() {
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress("localhost" + ":" + 5701);
        config.getGroupConfig().setName("distribution-app");
        final SerializerConfig dolphinEventSerializerConfig = new SerializerConfig().
                setImplementation(new CustomStreamSerializer()).setTypeClass(CustomEventFormat.class);
        config.getSerializationConfig().getSerializerConfigs().add(dolphinEventSerializerConfig);
        return HazelcastClient.newHazelcastClient(config);
    }

}
