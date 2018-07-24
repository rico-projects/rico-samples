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
package com.canoo.platform.samples.lazyloading;

import com.canoo.platform.remoting.RemotingBean;
import com.canoo.platform.remoting.ObservableList;
import com.canoo.platform.remoting.Property;

@RemotingBean
public class LazyLoadingBean {

    private Property<Integer> requestedSize;

    private Property<Boolean> loading;

    private ObservableList<LazyLoadingItem> items;

    public Integer getRequestedSize() {
        return requestedSize.get();
    }

    public Property<Integer> requestedSizeProperty() {
        return requestedSize;
    }

    public void setRequestedSize(Integer requestedSize) {
        this.requestedSize.set(requestedSize);
    }

    public Boolean getLoading() {
        return loading.get();
    }

    public Property<Boolean> loadingProperty() {
        return loading;
    }

    public void setLoading(Boolean loading) {
        this.loading.set(loading);
    }

    public ObservableList<LazyLoadingItem> getItems() {
        return items;
    }
}
