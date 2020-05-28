package dev.rico.samples.http.server;

import dev.rico.metrics.Metrics;
import dev.rico.metrics.types.Timer;
import dev.rico.server.timing.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/sample")
public class SampleEndpoint {

    private final Metrics metrics;

    @Autowired
    public SampleEndpoint(final Metrics metrics) {
        this.metrics = metrics;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doMetrics() throws Exception {
        final Timer timer = metrics.getOrCreateTimer("Sample-Timer");
        final long start = System.currentTimeMillis();
        Thread.sleep(2000);
        timer.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
    }
}
