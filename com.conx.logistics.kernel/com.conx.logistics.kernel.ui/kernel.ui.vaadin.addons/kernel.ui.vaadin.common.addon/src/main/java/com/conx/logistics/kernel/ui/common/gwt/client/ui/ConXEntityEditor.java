package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class ConXEntityEditor extends VerticalSplitPanel {
	private static final long serialVersionUID = 392870376541L;
	private ConXEntityGrid master;
	private VerticalLayout masterLayout;
	private TabSheet detail;
	
	public ConXEntityEditor() {
		setSizeFull();
		setStyleName("conx-entity-editor");
		
		initialize();
	}

	private void initialize() {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("Moo", String.class, "Cow");
		container.addContainerProperty("Meow", String.class, "Cat");
		container.addContainerProperty("Squeak", String.class, "Mouse");
		container.addContainerProperty("Oink", String.class, "Pig");
		Item item = container.addItem(0);
		item.getItemProperty("Moo").setValue("Cow");
		item.getItemProperty("Meow").setValue("Cat");
		item.getItemProperty("Squeak").setValue("Mouse");
		item.getItemProperty("Oink").setValue("Pig");
		Item item1 = container.addItem(1);
		item1.getItemProperty("Moo").setValue("Cow");
		item1.getItemProperty("Meow").setValue("Cat");
		item1.getItemProperty("Squeak").setValue("Mouse");
		item1.getItemProperty("Oink").setValue("Pig");

		master = new ConXEntityGrid(container);
		
		masterLayout = new VerticalLayout();
		masterLayout.setSizeFull();
		masterLayout.addComponent(new ConXEntityToolStrip());
		masterLayout.addComponent(master);
		masterLayout.setExpandRatio(master, 1.0f);
		
		detail = new TabSheet();
		detail.setStyleName("conx-entity-editor-detail-tabsheet");
		detail.setSizeFull();
		
		detail.addTab(new ConXEntityEditorFormLayout(item), "Detail Category 1");
		detail.addTab(new ConXEntityEditorFormLayout(item1), "Detail Category 2");
		
		setFirstComponent(masterLayout);
		setSecondComponent(detail);
		setSplitPosition(20, true);
	}
}
