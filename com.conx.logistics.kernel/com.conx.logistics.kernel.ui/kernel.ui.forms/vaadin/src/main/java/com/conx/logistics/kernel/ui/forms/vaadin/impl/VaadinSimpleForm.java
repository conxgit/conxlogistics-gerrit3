package com.conx.logistics.kernel.ui.forms.vaadin.impl;

import com.conx.logistics.kernel.ui.components.domain.form.ConXSimpleForm;
import com.conx.logistics.kernel.ui.forms.vaadin.IVaadinForm;
import com.vaadin.data.Item;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;

public class VaadinSimpleForm extends Form implements IVaadinForm {
	private static final long serialVersionUID = -639931L;

	private GridLayout layout;

	public VaadinSimpleForm(ConXSimpleForm componentForm) {
		layout = new VaadinFormGridLayout();
		layout.setSizeFull();

		setLayout(layout);
		// False so that commit() must be called explicitly
		setWriteThrough(false);
		// Disallow invalid data from acceptance by the container
		setInvalidCommitted(false);
	}
	
	@Override
	protected void attachField(Object propertyId, Field field) {
        if (propertyId == null || field == null) {
            return;
        }
        layout.addComponent(field);
    }
	
	@Override
	public void setItemDataSource(Item newDataSource) {
        setItemDataSource(newDataSource,
                newDataSource != null ? newDataSource.getItemPropertyIds()
                        : null);
    }
}
