package com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.header;

import java.util.ArrayList;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

public class EntityEditorBreadCrumb extends HorizontalLayout {
	private static final long serialVersionUID = 4323855996149506775L;
	
	private ArrayList<EntityEditorBreadCrumbItem> items;
	
	private HorizontalLayout leftLayout;

	public EntityEditorBreadCrumb() {
		setStyleName("conx-entity-editor-bread-crumb");
		setHeight("33px");
		setWidth("100%");
		
		this.leftLayout = new HorizontalLayout();
		leftLayout.setSpacing(true);
		addComponent(leftLayout);
		setComponentAlignment(leftLayout, Alignment.MIDDLE_LEFT);
		
		this.items = new ArrayList<EntityEditorBreadCrumbItem>();
	}
	
	public void addItem(boolean isGrid, String title) {
		EntityEditorBreadCrumbItem newItem = new EntityEditorBreadCrumbItem(isGrid, title);
		newItem.setSelected(true);
		if (items.size() != 0) {
			leftLayout.addComponent(new EntityEditorBreadCrumbSeparator());
			items.get(items.size() - 1).setSelected(false);
		}
		leftLayout.addComponent(newItem);
		items.add(newItem);
	}
}
