package com.conx.logistics.kernel.ui.components.domain.masterdetail;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class LineEditorContainerComponent extends AbstractConXComponent {
	
	@OneToMany(mappedBy="mainComponent")
	private Set<LineEditorComponent> lineEditors = new HashSet<LineEditorComponent>();

	public LineEditorContainerComponent(String code, String name) {
		this();
		setCode(code);
		setName(name);
	}
	
	public LineEditorContainerComponent() {
		super("lineeditorcontainercomponent");
	}

	public Set<LineEditorComponent> getLineEditors() {
		return lineEditors;
	}

	public void setLineEditors(Set<LineEditorComponent> lineEditors) {
		this.lineEditors = lineEditors;
	}  
}
