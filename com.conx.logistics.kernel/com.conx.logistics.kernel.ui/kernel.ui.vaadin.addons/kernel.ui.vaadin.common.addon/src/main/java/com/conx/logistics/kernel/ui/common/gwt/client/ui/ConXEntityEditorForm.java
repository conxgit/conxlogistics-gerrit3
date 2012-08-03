package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;

public class ConXEntityEditorForm extends Form {
	private static final long serialVersionUID = 100000002L;

	private GridLayout layout;
	
	public ConXEntityEditorForm() {
		layout = new GridLayout(4, 1);
		layout.setSizeFull();
		layout.setSpacing(true);
		layout.setMargin(true);
		setLayout(layout);
		setWriteThrough(false); // False so that commit() must be called explicitly
		setInvalidCommitted(false);
		setFormFieldFactory(new ConXEntityEditorFormFieldFactory());
	}
}
