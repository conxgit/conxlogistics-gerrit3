package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import com.vaadin.data.Item;
import com.vaadin.ui.VerticalLayout;

public class ConXEntityEditorFormLayout extends VerticalLayout {
	private static final long serialVersionUID = 141500988980L;
	
	private ConXEntityEditorFormHeader header;
	private ConXEntityEditorForm form;
	private ConXEntityEditorFormSection formSection;
	
	public ConXEntityEditorFormLayout(Item item) {
		setSizeFull();
		setStyleName("conx-entity-editor-form");
		initialize(item);
	}

	private void initialize(Item item) {
		this.header = new ConXEntityEditorFormHeader();
		this.form = new ConXEntityEditorForm();
		addComponent(header);
		this.formSection = new ConXEntityEditorFormSection();
		formSection.setItemDataSource(item);
		formSection.setWidth("100%");
		form.addFormSection("That Form Swag", formSection);
		addComponent(form);
		setExpandRatio(form, 1.0f);
	}
}
