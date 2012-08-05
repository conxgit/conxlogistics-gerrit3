package com.conx.logistics.kernel.ui.components.domain;

import javax.persistence.MappedSuperclass;

import com.conx.logistics.mdm.domain.MultitenantBaseEntity;

@MappedSuperclass
public abstract class BaseComponent extends MultitenantBaseEntity {
	
	private Boolean visibleByDefault = true;
	
	private String width;
	private String height;
	
	
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
}
