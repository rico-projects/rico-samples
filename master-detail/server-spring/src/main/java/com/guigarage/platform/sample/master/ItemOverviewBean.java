package com.guigarage.platform.sample.master;

import com.canoo.platform.remoting.Property;
import com.canoo.platform.remoting.RemotingBean;

import java.util.Objects;

@RemotingBean
public class ItemOverviewBean {

    public ItemOverviewBean() {

    }

    private Property<Long> id;

    private Property<String> name;

    private Property<String> description;

    public String getName() {
        return name.get();
    }

    public Property<String> nameProperty() {
        return name;
    }

    public void setName(final String name) {
        this.name.set(name);
    }

    public Long getId() {
        return id.get();
    }

    public Property<Long> idProperty() {
        return id;
    }

    public void setId(final Long id) {
        this.id.set(id);
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

    @Override
    public int hashCode() {
        return Objects.hash(id.get(), name.get(), description.get());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ItemOverviewBean other = (ItemOverviewBean) obj;
        return Objects.equals(this.id.get(), other.id.get())
                && Objects.equals(this.name.get(), other.name.get())
                && Objects.equals(this.description.get(), other.description.get());
    }

    @Override
    public String toString() {
        return "ItemOverviewBean{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                ", description=" + description.get() +
                '}';
    }
}
