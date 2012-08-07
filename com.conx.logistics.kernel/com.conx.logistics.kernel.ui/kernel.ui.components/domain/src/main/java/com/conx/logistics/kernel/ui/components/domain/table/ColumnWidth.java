package com.conx.logistics.kernel.ui.components.domain.table;

import java.io.Serializable;

import javax.persistence.Entity;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class ColumnWidth extends AbstractConXComponent implements Serializable {

    private String columnName;
	
	public ColumnWidth(String columnName,
			String width) {
		this();
		this.columnName = columnName;
		setWidth(width);
	}

	public ColumnWidth() {
		super("columnWidth");
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
