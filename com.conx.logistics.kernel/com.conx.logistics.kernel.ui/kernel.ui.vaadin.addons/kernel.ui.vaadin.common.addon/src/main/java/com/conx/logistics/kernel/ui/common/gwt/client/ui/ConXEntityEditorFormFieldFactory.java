package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

public class ConXEntityEditorFormFieldFactory extends DefaultFieldFactory {
	private static final long serialVersionUID = -835746547857641L;

	public Field createField(Item item, Object propertyId, Component uiContext) {
		return super.createField(item, propertyId, uiContext);
	}

}
