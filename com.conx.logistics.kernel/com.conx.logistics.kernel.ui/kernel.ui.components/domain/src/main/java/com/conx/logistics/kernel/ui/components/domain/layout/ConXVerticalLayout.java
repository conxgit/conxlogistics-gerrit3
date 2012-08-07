package com.conx.logistics.kernel.ui.components.domain.layout;

import javax.persistence.Entity;

@Entity
public class ConXVerticalLayout extends AbstractConXOrderedLayout {

    public ConXVerticalLayout() {
        super("verticalLayout");
        setWidth("100%");
    }
}
