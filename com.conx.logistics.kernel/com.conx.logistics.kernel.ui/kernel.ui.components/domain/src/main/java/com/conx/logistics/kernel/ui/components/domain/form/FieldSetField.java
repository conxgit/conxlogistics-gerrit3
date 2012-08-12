package com.conx.logistics.kernel.ui.components.domain.form;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class FieldSetField extends AbstractConXComponent implements Serializable {

	@OneToOne
	private FieldSet fieldSet;
	
	@OneToOne
	private DataSourceField field;
    
	
	public FieldSetField(FieldSet fieldSet, DataSourceField field) {
		this();
		this.field = field;
		this.fieldSet = fieldSet;
	}

	public FieldSetField() {
		super("fieldSetField");
	}

	public FieldSet getFieldSet() {
		return fieldSet;
	}

	public void setFieldSet(FieldSet fieldSet) {
		this.fieldSet = fieldSet;
	}

	public DataSourceField getField() {
		return field;
	}

	public void setField(DataSourceField field) {
		this.field = field;
	}
}
