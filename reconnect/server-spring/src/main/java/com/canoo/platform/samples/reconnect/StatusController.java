package com.canoo.platform.samples.reconnect;

import com.canoo.platform.remoting.server.RemotingContext;
import com.canoo.platform.remoting.server.RemotingController;
import com.canoo.platform.remoting.server.RemotingModel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;

import static com.canoo.platform.samples.reconnect.RemotingConstants.STATUS_CONTROLLER_NAME;

@RemotingController(STATUS_CONTROLLER_NAME)
public class StatusController {

    @RemotingModel
    private StatusBean model;

    @Inject
    private RemotingContext context;

    @PostConstruct
    public void init() {
        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                Thread.sleep(1_000);
                context.createSessionExecutor().runLaterInClientSession(() -> model.setTime(ZonedDateTime.now()));
            }
        });
    }
}
