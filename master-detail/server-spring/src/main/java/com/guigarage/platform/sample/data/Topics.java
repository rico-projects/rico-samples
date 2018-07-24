package com.guigarage.platform.sample.data;

import com.canoo.platform.remoting.server.event.Topic;

public interface Topics {

    Topic<String> CLEAR = Topic.create("clear");

    Topic<DataItem> REMOVE_LAST = Topic.create("removeLast");

    Topic<DataItem> NEW_ITEM = Topic.create("add");

}
