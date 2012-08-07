package com.conx.logistics.kernel.ui.components.domain.table;

import java.io.Serializable;

import javax.persistence.Entity;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.types.Alignment;

@Entity
public class ColumnIconPath extends AbstractConXComponent implements Serializable {

    private String columnName;
    
    private String iconPath;
	
	public ColumnIconPath(String columnName,
			String iconPath) {
		this();
		this.columnName = columnName;
		this.iconPath = iconPath;
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

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
}