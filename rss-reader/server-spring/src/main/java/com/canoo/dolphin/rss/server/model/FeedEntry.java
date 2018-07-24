package com.canoo.dolphin.rss.server.model;

import com.canoo.platform.remoting.ObservableList;
import com.canoo.platform.remoting.Property;
import com.canoo.platform.remoting.RemotingBean;


@RemotingBean
public class FeedEntry {

    private ObservableList<FeedItem> items;

    private Property<String> name;

    private Property<String> url;

    public ObservableList<FeedItem> getItems() {
        return items;
    }

    public String getName() {
        return name.get();
    }

    public Property<String> nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getUrl() {
        return url.get();
    }

    public Property<String> urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public FeedEntry withName(final String name) {
        setName(name);
        return this;
    }

    public FeedEntry withUrl(final String url) {
        setUrl(url);
        return this;
    }
}
