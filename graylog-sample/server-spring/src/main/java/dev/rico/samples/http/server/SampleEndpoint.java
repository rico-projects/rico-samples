package dev.rico.samples.http.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@RestController
@RequestMapping("/api/sample")
public class SampleEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(SampleEndpoint.class);

    @RequestMapping(method = RequestMethod.GET)
    public void doLogging() {
        LOG.debug("This is the only endpoint available");
        LOG.info("Endpoint called at {}", Clock.systemDefaultZone().instant());
        LOG.warn("Warn about broken thing");
        LOG.error("Now we are in trouble");
    }
}
