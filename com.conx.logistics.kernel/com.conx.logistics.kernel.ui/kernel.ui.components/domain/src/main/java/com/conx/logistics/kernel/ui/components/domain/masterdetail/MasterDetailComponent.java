package com.conx.logistics.kernel.ui.components.domain.masterdetail;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.table.ConXTable;

@Entity
public class MasterDetailComponent extends AbstractConXComponent {
	@ManyToOne
	private ConXTable table;	
	
	@OneToMany
	private LineEditorContainerComponent lineEditorPanel;

	public MasterDetailComponent() {
		super("masterdetailcomponent");
	}

	public MasterDetailComponent(ConXTable table,
			LineEditorContainerComponent lineEditorPanel) {
		this();
		this.table = table;
		this.lineEditorPanel = lineEditorPanel;
	} 
}
