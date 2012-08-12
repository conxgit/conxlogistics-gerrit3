package com.conx.logistics.kernel.ui.components.domain.layout;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class OrderedLayoutComponent extends AbstractConXComponent implements Serializable {

	@OneToOne
    private AbstractConXOrderedLayout orderedLayout;
	
	@OneToOne
	private AbstractConXComponent component;
    
	private float ratio = 1.0f;
	
	public OrderedLayoutComponent(AbstractConXOrderedLayout orderLayout,
			AbstractConXComponent component) {
		this();
		this.component = component;
	}

	public OrderedLayoutComponent() {
		super("orderedLayoutComponent");
	}
}
