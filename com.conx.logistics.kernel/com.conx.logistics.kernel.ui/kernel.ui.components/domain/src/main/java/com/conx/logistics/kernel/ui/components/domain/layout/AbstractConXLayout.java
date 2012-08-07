package com.conx.logistics.kernel.ui.components.domain.layout;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.MarginInfo;

@Entity
public abstract class AbstractConXLayout extends AbstractConXComponent {
	
	@ManyToOne
	private MarginInfo margins;
	
    public AbstractConXLayout(String typeId) {
        super(typeId);
    }	

    public AbstractConXLayout() {
        super("abstractLayout");
    }
}
