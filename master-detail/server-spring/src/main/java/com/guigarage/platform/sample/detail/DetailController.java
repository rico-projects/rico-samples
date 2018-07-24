package com.guigarage.platform.sample.detail;

import com.canoo.platform.remoting.server.RemotingAction;
import com.canoo.platform.remoting.server.RemotingController;
import com.canoo.platform.remoting.server.RemotingModel;
import com.guigarage.platform.sample.data.DataItem;
import com.guigarage.platform.sample.data.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RemotingController("DetailController")
public class DetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DetailController.class);

    @RemotingModel
    private DetailBean model;

    private final DataService dataService;

    @Autowired
    public DetailController(final DataService dataService) {
        this.dataService = dataService;
    }

    @PostConstruct
    public void init() {
        reload();
        System.out.println("DetailController created");
    }

    @PreDestroy
    public void bye() {
        System.out.println("DetailController destroyed");
    }

    @RemotingAction("reload")
    public void reload() {
        Long id = model.getId();
        LOGGER.debug("Reload with id {}", id);
        if(id != null) {
            final DataItem item = dataService.find(id);
            model.setName(item.getName());
            model.setDescription(item.getDescription());
        } else {
            model.setName("");
            model.setDescription("");
        }
    }
}
