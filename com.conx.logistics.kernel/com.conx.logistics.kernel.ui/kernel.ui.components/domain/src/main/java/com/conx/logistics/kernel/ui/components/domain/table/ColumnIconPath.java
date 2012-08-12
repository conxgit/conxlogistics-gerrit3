package com.conx.logistics.kernel.ui.components.domain.table;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.types.Alignment;

@Entity
public class ColumnIconPath extends AbstractConXComponent implements Serializable {
	
	@ManyToOne 
	private ConXTable table;

    private String columnName;
	
	public ColumnIconPath(String columnName,
			String iconPath) {
		this();
		this.columnName = columnName;
		setIconPath(iconPath);
	}

	public ColumnIconPath() {
		super("columnIconPath");
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
