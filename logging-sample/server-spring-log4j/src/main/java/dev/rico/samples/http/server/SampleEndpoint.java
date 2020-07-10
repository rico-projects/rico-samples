package dev.rico.samples.http.server;

import dev.rico.core.context.RicoApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@RestController
@RequestMapping("/api/sample")
public class SampleEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(SampleEndpoint.class);

    private final RicoApplicationContext ricoContext;

    @Autowired
    public SampleEndpoint(RicoApplicationContext ricoApplicationContext) {
        this.ricoContext = ricoApplicationContext;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doLogging() {

        ricoContext.setThreadLocalAttribute("RicoAppName", "Graylog Test App");

        LOG.debug("This is the only endpoint available");
        LOG.info("Endpoint called at {}", Clock.systemDefaultZone().instant());
        LOG.warn("Warn about broken thing");
        LOG.error("Now we are in trouble");
    }
}
