package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.header.EntityEditorBreadCrumb;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.header.EntityEditorToolStrip;
import com.vaadin.ui.VerticalLayout;

public class EntityTableHeaderView extends VerticalLayout implements IEntityTableHeaderView {
	private static final long serialVersionUID = -8556644797413509062L;
	
	private EntityEditorToolStrip toolstrip;
	private EntityEditorBreadCrumb breadcrumb;
	
	@UiField
	VerticalLayout mainLayout;

	public EntityTableHeaderView() {
		setWidth("100%");
		setHeight("73px");
	}
	
	@Override
	public void init() {
		this.toolstrip = new EntityEditorToolStrip();
		this.breadcrumb = new EntityEditorBreadCrumb();
		
		breadcrumb.addItem(true, "Receive");
		breadcrumb.addItem(false, "Receive Line #2");
		breadcrumb.addItem(false, "Product");
		
		mainLayout.addComponent(toolstrip);
		mainLayout.addComponent(breadcrumb);
	}
}
