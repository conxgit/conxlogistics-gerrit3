package com.conx.logistics.kernel.ui.components.domain.masterdetail;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class LineEditorComponent extends AbstractConXComponent {
	@OneToOne
	private AbstractConXComponent mainComponent;

	public LineEditorComponent() {
		super("lineeditorcomponent");
	}
}
