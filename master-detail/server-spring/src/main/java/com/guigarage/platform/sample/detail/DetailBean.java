package com.guigarage.platform.sample.detail;

import com.canoo.platform.remoting.Property;
import com.canoo.platform.remoting.RemotingBean;

@RemotingBean
public class DetailBean {

    private Property<Long> id;

    private Property<String> name;

    private Property<String> description;

    public Long getId() {
        return id.get();
    }

    public Property<Long> idProperty() {
        return id;
    }

    public void setId(final Long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public Property<String> nameProperty() {
        return name;
    }

    public void setName(final String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public Property<String> descriptionProperty() {
        return description;
    }

    public void setDescription(final String description) {
        this.description.set(description);
    }
}
