package com.conx.logistics.kernel.ui.forms.vaadin.impl;

import java.util.HashMap;

import com.conx.logistics.kernel.ui.components.domain.form.ConXCollapseableSectionForm;
import com.conx.logistics.kernel.ui.components.domain.form.FieldSet;
import com.conx.logistics.kernel.ui.forms.vaadin.IVaadinForm;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;

public class VaadinCollapsibleSectionForm extends Form implements IVaadinForm {
	private static final long serialVersionUID = -639931L;

	private VerticalLayout layout;
	private ConXCollapseableSectionForm componentForm;
	private HashMap<FieldSet, VaadinCollapsibleSectionFormSectionHeader> headers;

	public VaadinCollapsibleSectionForm(ConXCollapseableSectionForm componentForm) {
		this.componentForm = componentForm;
		headers = new HashMap<FieldSet, VaadinCollapsibleSectionFormSectionHeader>();
		layout = new VerticalLayout();
		layout.setSizeFull();

		setLayout(layout);
		// False so that commit() must be called explicitly
		setWriteThrough(false);
		// Disallow invalid data from acceptance by the container
		setInvalidCommitted(false);
	}

	@Override
	protected void attachField(Object propertyId, Field field)  {
		FieldSet fieldSet = componentForm.getFieldSetForField((String) propertyId);
		VaadinCollapsibleSectionFormSectionHeader header = headers.get(fieldSet);
		if (header == null) {
			header = addFormSection(fieldSet);
		}
		header.getLayout().addComponent(field);
	}
	
	private VaadinCollapsibleSectionFormSectionHeader addFormSection(FieldSet fieldSet) {
		VaadinFormGridLayout content = new VaadinFormGridLayout();
		VaadinCollapsibleSectionFormSectionHeader header = new VaadinCollapsibleSectionFormSectionHeader(fieldSet, content);
		layout.addComponent(header);
		layout.addComponent(content);
		headers.put(fieldSet, header);
		return header;
	}
}
