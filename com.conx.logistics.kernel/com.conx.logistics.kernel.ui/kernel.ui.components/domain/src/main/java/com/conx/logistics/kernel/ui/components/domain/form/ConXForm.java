package com.conx.logistics.kernel.ui.components.domain.form;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXField;
import com.conx.logistics.kernel.ui.components.domain.layout.AbstractConXLayout;

@Entity
public abstract class ConXForm extends AbstractConXField {

	@OneToOne
	private AbstractConXLayout layout;

	public ConXForm(String typeId) {
		super(typeId);
	}	

	public AbstractConXLayout getLayout() {
		return layout;
	}

	public void setLayout(AbstractConXLayout layout) {
		this.layout = layout;
	}
}
