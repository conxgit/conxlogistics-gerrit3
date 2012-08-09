package com.conx.logistics.kernel.ui.components.domain.masterdetail;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class LineEditorComponent extends AbstractConXComponent {
	@OneToOne
	private LineEditorContainerComponent mainComponent;
	
	public LineEditorComponent(String code, String caption,LineEditorContainerComponent mainComponent) {
		this();
		setCode(code);
		setName(caption);
		setCaption(caption);
		this.mainComponent = mainComponent;
	}

	public LineEditorComponent() {
		super("lineeditorcomponent");
	}

	public LineEditorComponent(LineEditorContainerComponent mainComponent) {
		this();
		this.mainComponent = mainComponent;
	}
}
