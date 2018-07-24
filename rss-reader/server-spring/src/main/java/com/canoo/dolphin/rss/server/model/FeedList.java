package com.canoo.dolphin.rss.server.model;

import com.canoo.platform.remoting.ObservableList;
import com.canoo.platform.remoting.RemotingBean;

@RemotingBean
public class FeedList {

    private ObservableList<FeedEntry> feedEntries;

    public ObservableList<FeedEntry> getFeedEntries() {
        return feedEntries;
    }
}
