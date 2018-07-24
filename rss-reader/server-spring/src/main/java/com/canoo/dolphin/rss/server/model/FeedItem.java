package com.canoo.dolphin.rss.server.model;

import com.canoo.platform.remoting.Property;
import com.canoo.platform.remoting.RemotingBean;

@RemotingBean
public class FeedItem {

    private Property<String> text;

    private Property<String> time;

    public String getText() {
        return text.get();
    }

    public Property<String> textProperty() {
        return text;
    }

    public String getTime() {
        return time.get();
    }

    public Property<String> timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public void setText(final String text) {
        this.text.set(text);
    }

    public FeedItem withText(final String text) {
        setText(text);
        return this;
    }

    public FeedItem withTime(final String time) {
        setTime(time);
        return this;
    }
}
