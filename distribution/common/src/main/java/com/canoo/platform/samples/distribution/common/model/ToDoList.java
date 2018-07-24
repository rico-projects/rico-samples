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
package com.canoo.platform.samples.distribution.common.model;

import com.canoo.platform.remoting.ObservableList;
import com.canoo.platform.remoting.RemotingBean;
import com.canoo.platform.remoting.Property;

@RemotingBean
public class ToDoList {

    private ObservableList<String> items;

    private Property<String> newItemText;

    public ObservableList<String> getItems() {
        return items;
    }

    public String getNewItemText() {
        return newItemText.get();
    }

    public Property<String> newItemTextProperty() {
        return newItemText;
    }

    public void setNewItemText(String newItemText) {
        this.newItemText.set(newItemText);
    }
}
