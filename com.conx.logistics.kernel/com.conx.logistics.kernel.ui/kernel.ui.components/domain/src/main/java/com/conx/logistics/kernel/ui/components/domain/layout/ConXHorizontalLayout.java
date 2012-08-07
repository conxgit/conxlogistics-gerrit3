package com.conx.logistics.kernel.ui.components.domain.layout;

import javax.persistence.Entity;

@Entity
public class ConXHorizontalLayout extends AbstractConXOrderedLayout {

    public ConXHorizontalLayout() {
        super("horizontalLayout");
        setWidth("100%");
    }
}
