package com.guigarage.platform.sample.master;

import com.canoo.platform.remoting.ObservableList;
import com.canoo.platform.remoting.Property;
import com.canoo.platform.remoting.RemotingBean;

@RemotingBean
public class MasterBean {

    private ObservableList<ItemOverviewBean> items;

    private Property<NestedBean> nestedBean;

    public ObservableList<ItemOverviewBean> getItems() {
        return items;
    }

    public NestedBean getNestedBean() {
        return nestedBean.get();
    }

    public void setNestedBean(final NestedBean nestedBean) {
        this.nestedBean.set(nestedBean);
    }
}
