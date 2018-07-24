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
package com.canoo.dolphin.samples.processmonitor;

import com.canoo.dolphin.todo.server.AsyncServerRunner;
import com.canoo.platform.remoting.server.spring.DolphinPlatformRemotingApplication;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

@DolphinPlatformRemotingApplication
public class ProcessMonitorServer {

    public static void main(String... args) {
        SpringApplication.run(ProcessMonitorServer.class, args);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AsyncServerRunner createRunner() {
        final List<Runnable> myTasks = new CopyOnWriteArrayList<>();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (!myTasks.isEmpty()) {
                        Runnable task = myTasks.remove(0);
                        task.run();
                    }
                }
            }
        });

        return new AsyncServerRunner() {
            @Override
            public void execute(Runnable task) {
                myTasks.add(task);
            }
        };
    }

}
