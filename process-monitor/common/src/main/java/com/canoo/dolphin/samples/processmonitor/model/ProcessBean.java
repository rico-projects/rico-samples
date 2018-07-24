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
package com.canoo.dolphin.samples.processmonitor.model;

import com.canoo.platform.remoting.RemotingBean;
import com.canoo.platform.remoting.Property;

@RemotingBean
public class ProcessBean {

    private Property<String> processID;

    private Property<String> name;

    private Property<String> cpuPercentage;

    private Property<String> memoryPercentage;

    public Property<String> processIDProperty() {
        return processID;
    }

    public Property<String> nameProperty() {
        return name;
    }

    public Property<String> cpuPercentageProperty() {
        return cpuPercentage;
    }

    public Property<String> memoryPercentageProperty() {
        return memoryPercentage;
    }

    public void setProcessID(String processID) {
        this.processID.set(processID);
    }

    public void setCpuPercentage(String cpuPercentage) {
        this.cpuPercentage.set(cpuPercentage);
    }

    public void setMemoryPercentage(String memoryPercentage) {
        this.memoryPercentage.set(memoryPercentage);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getProcessID() {
        return processID.get();
    }

    public String getName() {
        return name.get();
    }

    public String getCpuPercentage() {
        return cpuPercentage.get();
    }

    public String getMemoryPercentage() {
        return memoryPercentage.get();
    }
}
