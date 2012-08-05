package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import java.util.ArrayList;

import com.vaadin.ui.VerticalLayout;

public class ConXEntityEditorForm extends VerticalLayout {
	private static final long serialVersionUID = 2980988998881L;

	private ArrayList<ConXEntityEditorFormSeparator> separators;
	private ArrayList<ConXEntityEditorFormSection> sections;
	
	public ConXEntityEditorForm() {
		separators = new ArrayList<ConXEntityEditorFormSeparator>();
		sections = new ArrayList<ConXEntityEditorFormSection>();
		setSizeFull();
		setSpacing(true);
	}
	
	public void addFormSection(String title, ConXEntityEditorFormSection section) {
		ConXEntityEditorFormSeparator separator = new ConXEntityEditorFormSeparator(title, section);
		addComponent(separator);
		addComponent(section);
		
		separators.add(separator);
		sections.add(section);
	}
}
