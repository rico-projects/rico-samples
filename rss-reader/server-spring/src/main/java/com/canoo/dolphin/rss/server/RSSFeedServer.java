package com.canoo.dolphin.rss.server;

import com.canoo.platform.remoting.server.spring.DolphinPlatformRemotingApplication;
import org.springframework.boot.SpringApplication;

@DolphinPlatformRemotingApplication
public class RSSFeedServer {

    public static void main(String... args) {
        SpringApplication.run(RSSFeedServer.class, args);
    }
}
