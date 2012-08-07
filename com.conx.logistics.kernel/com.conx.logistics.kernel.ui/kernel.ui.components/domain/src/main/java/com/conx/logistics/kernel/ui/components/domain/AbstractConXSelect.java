package com.conx.logistics.kernel.ui.components.domain;

import javax.persistence.Entity;

import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXField;

@Entity
public abstract class AbstractConXSelect extends AbstractConXField {
    private boolean multiSelect = false; 

	public AbstractConXSelect(String typeId) {
		super(typeId);
	}
	
}
