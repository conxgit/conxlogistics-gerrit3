package com.conx.logistics.kernel.ui.components.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ComponentExpandRatio extends AbstractConXComponent implements Serializable {

	@OneToOne
    private AbstractConXComponent component;
    
	private float ratio = 1.0f;
	
	public ComponentExpandRatio(AbstractConXComponent component,
			float ratio) {
		this();
		this.component = component;
		this.ratio = ratio;
	}

	public ComponentExpandRatio() {
		super("componentExpandRatio");
	}
}
