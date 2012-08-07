package com.conx.logistics.kernel.ui.components.domain.table;

import java.io.Serializable;

import javax.persistence.Entity;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.types.Alignment;

@Entity
public class ColumnAlignment extends AbstractConXComponent implements Serializable {

    private String columnName;
    
    private Alignment alignment;
	
	public ColumnAlignment(String columnName,
			Alignment alignment) {
		this();
		this.columnName = columnName;
		this.alignment = alignment;
	}

	public ColumnAlignment() {
		super("columnAlignment");
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
}
