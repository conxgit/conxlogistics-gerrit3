package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import com.vaadin.data.Item;
import com.vaadin.ui.VerticalLayout;

public class ConXEntityEditorFormLayout extends VerticalLayout {
	private static final long serialVersionUID = 141500988980L;
	
	private ConXEntityEditorFormHeader header;
	private ConXEntityEditorForm form;
	
	public ConXEntityEditorFormLayout(Item item) {
		setSizeFull();
		setStyleName("conx-entity-editor-form");
		initialize(item);
	}

	private void initialize(Item item) {
		this.header = new ConXEntityEditorFormHeader();
		addComponent(header);
		this.form = new ConXEntityEditorForm();
		form.setItemDataSource(item);
		form.setSizeFull();
		addComponent(form);
		setExpandRatio(form, 1.0f);
	}
}
