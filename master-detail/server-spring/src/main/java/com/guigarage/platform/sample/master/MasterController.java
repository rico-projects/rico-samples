package com.guigarage.platform.sample.master;

import com.canoo.platform.core.functional.Subscription;
import com.canoo.platform.remoting.BeanManager;
import com.canoo.platform.remoting.server.RemotingAction;
import com.canoo.platform.remoting.server.RemotingController;
import com.canoo.platform.remoting.server.RemotingModel;
import com.canoo.platform.remoting.server.event.RemotingEventBus;
import com.guigarage.platform.sample.data.DataItem;
import com.guigarage.platform.sample.data.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

import static com.guigarage.platform.sample.data.Topics.CLEAR;
import static com.guigarage.platform.sample.data.Topics.NEW_ITEM;
import static com.guigarage.platform.sample.data.Topics.REMOVE_LAST;

@RemotingController("MasterController")
public class MasterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterController.class);

    @RemotingModel
    private MasterBean model;

    private final BeanManager beanManager;

    private final DataService dataService;

    private final RemotingEventBus remotingEventBus;

    private List<Subscription> subs = new ArrayList<>();

    @Autowired
    public MasterController(final DataService dataService, final BeanManager beanManager, final RemotingEventBus remotingEventBus) {
        this.dataService = dataService;
        this.beanManager = beanManager;
        this.remotingEventBus = remotingEventBus;
    }

    @PostConstruct
    public void init() {

        final NestedBean firstNestedBean = beanManager.create(NestedBean.class);
        firstNestedBean.setName("1st");

        final NestedBean secondNestedBean = beanManager.create(NestedBean.class);
        secondNestedBean.setName("2nd");

        final NestedBean thirdNestedBean = beanManager.create(NestedBean.class);
        thirdNestedBean.setName("3nd");

        secondNestedBean.setOther(thirdNestedBean);
        firstNestedBean.setOther(secondNestedBean);

        model.setNestedBean(firstNestedBean);

        dataService.getItems().forEach(i -> internalAdd(i));
        subs.add(remotingEventBus.subscribe(CLEAR, message -> internalClear()));
        subs.add(remotingEventBus.subscribe(REMOVE_LAST, message -> internalRemoveLast(message.getData())));
        subs.add(remotingEventBus.subscribe(NEW_ITEM, message -> internalAdd(message.getData())));
        LOGGER.debug("Created Controller with {}", model.getItems());
    }

    @PreDestroy
    public void bye() {
        subs.forEach(Subscription::unsubscribe);
    }

    @RemotingAction("clear")
    public void clear() {
        LOGGER.debug("Action: clear");
        dataService.clear();
    }

    @RemotingAction("add")
    public void add() {
        LOGGER.debug("Action: add");
        dataService.add();
    }

    @RemotingAction("removeLast")
    public void removeLast() {
        LOGGER.debug("Action: removeLast");
        dataService.removeLast();
    }

    @RemotingAction("resetNested")
    public void resetNested() {
        LOGGER.debug("Action: resetNested");
        this.model.setNestedBean(null);

    }

    @RemotingAction("resetOtherNested")
    public void resetOtherNested() {
        LOGGER.debug("Action: resetOtherNested");
        if (this.model.getNestedBean() != null) {
            this.model.getNestedBean().setOther(null);
        }

    }

    @RemotingAction("setNested")
    public void setNested() {
        LOGGER.debug("Action: setNested");

        if (this.model.getNestedBean() == null) {

            final NestedBean firstNestedBean = beanManager.create(NestedBean.class);
            firstNestedBean.setName("1st next");

            final NestedBean secondNestedBean = beanManager.create(NestedBean.class);
            secondNestedBean.setName("2nd next");

            final NestedBean thirdNestedBean = beanManager.create(NestedBean.class);
            thirdNestedBean.setName("3nd next");

            secondNestedBean.setOther(thirdNestedBean);
            firstNestedBean.setOther(secondNestedBean);

            model.setNestedBean(firstNestedBean);
            LOGGER.debug("Model changed");
        } else {
            LOGGER.debug("Model not changed");
        }
    }

    private void internalRemoveLast(final DataItem removed) {
        LOGGER.debug("Removing last id:{}, name:{} from {}", removed.getId(), removed.getName(), model.getItems());
        if (model.getItems().size() >= 1) {
            try {
                model.getItems().remove(model.getItems().size() - 1);
            } catch (Exception e) {

            }
        }
    }

    private void internalAdd(final DataItem item) {
        final ItemOverviewBean bean = beanManager.create(ItemOverviewBean.class);
        bean.setId(item.getId());
        bean.setName(item.getName());
        bean.setDescription(item.getDescription());
        model.getItems().add(bean);
    }

    private void internalClear() {
        LOGGER.debug("Clearing model");
        model.getItems().clear();
    }
}
