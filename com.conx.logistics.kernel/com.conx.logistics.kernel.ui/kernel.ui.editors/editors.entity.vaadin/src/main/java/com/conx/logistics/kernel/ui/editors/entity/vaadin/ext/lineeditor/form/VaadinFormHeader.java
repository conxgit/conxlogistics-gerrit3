package com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.lineeditor.form;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class VaadinFormHeader extends HorizontalLayout {
	private static final long serialVersionUID = 5540232112L;
	private Label action;
	private Label title;

	public VaadinFormHeader() {
		setHeight("38px");
		setWidth("100%");
		setStyleName("conx-entity-editor-form-header");
		setSpacing(true);
		
		initialize();
	}

	private void initialize() {
		action = new Label();
		action.setStyleName("conx-entity-editor-form-header-action");
		
		title = new Label();
		title.setStyleName("conx-entity-editor-form-header-title");
		
		HorizontalLayout leftPanel = new HorizontalLayout();
		leftPanel.setHeight("38px");
		leftPanel.setSpacing(true);
		leftPanel.setStyleName("conx-entity-editor-form-header-left");
		leftPanel.setMargin(false, false, false, true);
		leftPanel.addComponent(action);
		leftPanel.addComponent(title);
		leftPanel.setComponentAlignment(action, Alignment.MIDDLE_LEFT);
		leftPanel.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		
		addComponent(leftPanel);
		setComponentAlignment(leftPanel, Alignment.TOP_LEFT);
	}

	public Label getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action.setCaption(action);
	}

	public Label getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.setCaption(title);
	}
}
