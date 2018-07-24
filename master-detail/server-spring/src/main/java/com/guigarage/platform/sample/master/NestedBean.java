package com.guigarage.platform.sample.master;

import com.canoo.platform.remoting.Property;
import com.canoo.platform.remoting.RemotingBean;

import java.util.Objects;

@RemotingBean
public class NestedBean {

    private Property<String> name;

    private Property<NestedBean> other;

    public String getName() {
        return name.get();
    }

    public void setName(final String name) {
        this.name.set(name);
    }

    public NestedBean getOther() {
        return other.get();
    }

    public void setOther(final NestedBean other) {
        this.other.set(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.get(), other.get());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final NestedBean other = (NestedBean) obj;
        return Objects.equals(this.name.get(), other.name.get())
                && Objects.equals(this.other.get(), other.other.get());
    }

    @Override
    public String toString() {
        return "NestedBean{" +
                "name=" + name.get() +
                ", other=" + other.get() +
                '}';
    }
}
