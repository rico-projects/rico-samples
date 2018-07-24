package com.canoo.dolphin.rss.server.service;

import com.canoo.platform.remoting.server.event.RemotingEventBus;
import com.canoo.platform.remoting.server.event.Topic;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FeedService {

    private static final Logger LOG = LoggerFactory.getLogger(FeedService.class);

    private final RemotingEventBus eventBus;

    private final List<String> feeds = Arrays.asList("http://www.spiegel.de/schlagzeilen/index.rss",
            "http://www.faz.net/rss/aktuell/");

    private final Map<String, SyndFeed> collectedFeeds = new ConcurrentHashMap<>();

    public FeedService(RemotingEventBus eventBus) {
        this.eventBus = eventBus;


        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() -> {
            feeds.forEach(url -> {
                try {
                    LOG.info("Fetching feed for: {}", url);
                    final URL feedUrl = new URL(url);

                    final SyndFeedInput input = new SyndFeedInput();
                    final SyndFeed feed = input.build(new XmlReader(feedUrl));

                    collectedFeeds.put(url, feed);

                    feed.setEntries(feed.getEntries().stream().sorted((first, second) -> {
                        if (first.getPublishedDate().after(second.getPublishedDate())) {
                            return -1;
                        } else if (first.getPublishedDate().before(second.getPublishedDate())) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }).collect(Collectors.toList()));

                    final Topic<String> topic = Topic.create("update");
                    eventBus.publish(topic, url);
                    LOG.info("Count: {}, {}", feed.getEntries().size(), feed.getEntries().get(0).getPublishedDate());


                } catch (IOException | FeedException exception) {
                    LOG.error("Could not fetch or parse RSS feed for: {}", url);
                }
            });

        }, 0, 30, TimeUnit.SECONDS);
    }

    public Stream<SyndFeed> getFeedsAsStream() {
        return collectedFeeds.values().stream();
    }
}
