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

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Singleton
public class BackgroundTaskRunnerImpl implements AsyncServerRunner {

    private List<Runnable> myTasks = new CopyOnWriteArrayList<>();

    @Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void update(){
        while (!myTasks.isEmpty()) {
            Runnable task = myTasks.remove(0);
            task.run();
        }
    }

    @Override
    public void execute(Runnable task) {
        myTasks.add(task);
    }
}
