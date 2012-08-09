package com.conx.logistics.kernel.ui.components.domain.masterdetail;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.table.ConXTable;

@Entity
public class MasterDetailComponent extends AbstractConXComponent {
	@ManyToOne
	private ConXTable table;	
	
	@OneToOne
	private LineEditorContainerComponent lineEditorPanel;

	public MasterDetailComponent() {
		super("masterdetailcomponent");
	}
	
	public MasterDetailComponent(String code, String name, DataSource ds) {
		this(code,name);
		setDataSource(ds);
	}		
	
	public MasterDetailComponent(String code, String name) {
		this();
		setCode(code);
		setName(name);
	}	

	public MasterDetailComponent(String name,
			ConXTable table,
			LineEditorContainerComponent lineEditorPanel) {
		this();
		this.table = table;
		this.lineEditorPanel = lineEditorPanel;
	}

	public ConXTable getTable() {
		return table;
	}

	public void setTable(ConXTable table) {
		this.table = table;
		this.setDataSource(getDataSource());
	}

	public LineEditorContainerComponent getLineEditorPanel() {
		return lineEditorPanel;
	}

	public void setLineEditorPanel(LineEditorContainerComponent lineEditorPanel) {
		this.lineEditorPanel = lineEditorPanel;
	} 
}
