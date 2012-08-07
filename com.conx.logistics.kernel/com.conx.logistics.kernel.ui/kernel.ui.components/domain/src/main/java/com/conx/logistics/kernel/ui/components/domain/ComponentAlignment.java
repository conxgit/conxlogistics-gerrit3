package com.conx.logistics.kernel.ui.components.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.ui.components.domain.types.Alignment;

@Entity
public class ComponentAlignment extends AbstractConXComponent implements Serializable {

	@OneToOne
    private AbstractConXComponent component;
    
	private Alignment alignment;
	
	public ComponentAlignment(AbstractConXComponent component,
			Alignment alignment) {
		this();
		this.component = component;
		this.alignment = alignment;
	}

	public ComponentAlignment() {
		super("componentAlignment");
	}
}
