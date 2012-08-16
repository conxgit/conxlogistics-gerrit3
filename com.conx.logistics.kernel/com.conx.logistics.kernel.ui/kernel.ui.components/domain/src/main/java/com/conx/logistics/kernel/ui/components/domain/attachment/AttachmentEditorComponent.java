package com.conx.logistics.kernel.ui.components.domain.attachment;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.table.ConXTable;

@Entity
public class AttachmentEditorComponent extends AbstractConXComponent {
	public AttachmentEditorComponent() {
		super("attachmenteditorcomponent");
	}
	
	public AttachmentEditorComponent(DataSource ds) {
		this();
		setDataSource(ds);
	}		
}
