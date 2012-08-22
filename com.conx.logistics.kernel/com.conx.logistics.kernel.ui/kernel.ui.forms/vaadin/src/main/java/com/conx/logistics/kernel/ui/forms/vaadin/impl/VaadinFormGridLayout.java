package com.conx.logistics.kernel.ui.forms.vaadin.impl;

import com.vaadin.ui.GridLayout;

class VaadinFormGridLayout extends GridLayout {
	private static final long serialVersionUID = -21016456L;

	public VaadinFormGridLayout() {
		super(4, 1);
		setWidth("100%");
		setSpacing(true);
		setMargin(true, false, false, true);
		setStyleName("conx-entity-editor-form");
	}
}
