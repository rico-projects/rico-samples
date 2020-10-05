package dev.rico.samples.http.server;

import dev.rico.core.concurrent.Scheduler;
import dev.rico.core.concurrent.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
class TopLogger implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TopLogger.class);

    private final Scheduler scheduler;

    @Autowired
    public TopLogger(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run(ApplicationArguments args) {
        final Duration fourSeconds = Duration.of(4, ChronoUnit.SECONDS);
        final Duration eightSeconds = Duration.of(8, ChronoUnit.SECONDS);
        final Duration fifteenSeconds = Duration.of(15, ChronoUnit.SECONDS);

        scheduler.schedule(() -> logTop("ping"), Trigger.in(fourSeconds));
        scheduler.schedule(() -> logTop("baz "), Trigger.nowAndEvery(fifteenSeconds));
        scheduler.schedule(() -> logTop("fiz "), Trigger.afterAndEvery(eightSeconds, fifteenSeconds));
        scheduler.schedule(() -> logTop("pong"), Trigger.every(eightSeconds));
    }

    private void logTop(String ping) {
        LOG.info(ping);
    }
}
