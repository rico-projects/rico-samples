package com.guigarage.platform.sample.workbench;


import com.canoo.platform.remoting.server.RemotingController;
import com.canoo.platform.remoting.server.RemotingModel;

import javax.annotation.PostConstruct;

@RemotingController("WorkbenchController")
public class WorkbenchController {

    @RemotingModel
    private WorkbenchBean model;

    @PostConstruct
    public void init() {

    }
}
