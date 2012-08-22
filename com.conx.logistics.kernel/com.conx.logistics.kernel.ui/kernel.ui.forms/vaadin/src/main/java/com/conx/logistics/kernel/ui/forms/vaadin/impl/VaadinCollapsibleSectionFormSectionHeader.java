package com.conx.logistics.kernel.ui.forms.vaadin.impl;

import com.conx.logistics.kernel.ui.components.domain.form.FieldSet;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

class VaadinCollapsibleSectionFormSectionHeader extends HorizontalLayout {
	private static final long serialVersionUID = 1010002929298L;
	
	private Embedded arrow;
	private Layout layout;
	private String caption;
	private Label label;
	private boolean expanded;

	public VaadinCollapsibleSectionFormSectionHeader(FieldSet fieldSet, Layout layout) {
		this.caption = fieldSet.getCaption();
		this.layout = layout;
		this.expanded = false;
		
		setWidth("100%");
		setHeight("25px");
		setStyleName("conx-entity-editor-form-separator");
		setExpanded(true);
		
		initialize();
	}

	private void initialize() {
		HorizontalLayout leftPanel = new HorizontalLayout();
		leftPanel.setHeight("25px");
		leftPanel.setSpacing(true);
		
		arrow = new Embedded();
		arrow.setStyleName("conx-entity-editor-form-separator-arrow");
		arrow.setWidth("18px");
		arrow.setHeight("10px");
		
		label = new Label();
		label.setStyleName("conx-entity-editor-form-separator-caption");
		label.setHeight("15px");
		label.setCaption(caption);
		
		leftPanel.addComponent(arrow);
		leftPanel.addComponent(label);
		addComponent(leftPanel);
		setComponentAlignment(leftPanel, Alignment.TOP_LEFT);
		
		this.addListener(new LayoutClickListener() {
			private static final long serialVersionUID = 1L;

			public void layoutClick(LayoutClickEvent event) {
				setExpanded(!expanded);
			}
		});
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
		if (!expanded) {
			arrow.removeStyleName("conx-entity-editor-form-separator-arrow-expanded");
		} else {
			arrow.addStyleName("conx-entity-editor-form-separator-arrow-expanded");
		}
		layout.setVisible(expanded);
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}
