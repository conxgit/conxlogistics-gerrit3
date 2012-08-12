package com.conx.logistics.kernel.ui.components.domain.form;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class FormField extends AbstractConXComponent implements Serializable {

	@OneToOne
	private ConXForm form;
	
	@OneToOne
	private DataSourceField field;
	
	public FormField(DataSourceField field) {
		this();
		this.field = field;
	}	
    
	
	public FormField(ConXForm form, DataSourceField field) {
		this();
		this.field = field;
		this.form = form;
	}

	public FormField() {
		super("formField");
	}

	public ConXForm getForm() {
		return form;
	}

	public void setForm(ConXForm form) {
		this.form = form;
	}

	public DataSourceField getField() {
		return field;
	}

	public void setField(DataSourceField field) {
		this.field = field;
	}
}
