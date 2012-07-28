package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import java.util.Collection;
import java.util.HashSet;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;

public class ConXNavigationAccordion extends VerticalLayout implements ValueChangeListener {
	private static final long serialVersionUID = 3370715870286189986L;

	private Accordion accordion;
	private HashSet<ValueChangeListener> navigationListeners;
	private Object currentValue;

	public ConXNavigationAccordion() {
		setSizeFull();
		setMargin(false, false, true, false);

		navigationListeners = new HashSet<ValueChangeListener>();
		accordion = new Accordion();
		accordion.setStyleName("conx-navigation-accordion");
		accordion.setSizeFull();

		addComponent(accordion);
	}

	public void addCategory(ConXNavigationTree subTree, String title) {
		subTree.setNavigationListener(this);
		accordion.addTab(subTree, title);
	}

	public void setContainer(HierarchicalContainer container) {
		Collection<?> ids = container.rootItemIds();
		
		for (Object id : ids) {
			Item currentItem = container.getItem(id);
			Object name = currentItem.getItemProperty("Name").getValue();
			if (name instanceof String) {
				ConXNavigationTree tree = new ConXNavigationTree();
				tree.setNavigationContainer(container, "Name", id);
				addCategory(tree, (String) name);
			}
		}
	}
	
	public void addNavigationListener(ValueChangeListener listener) {
		navigationListeners.add(listener);
	}
	
	public void clearNavigationListeners() {
		navigationListeners.clear();
	}

	public void valueChange(ValueChangeEvent event) {
		for (ValueChangeListener listener : navigationListeners) {
			listener.valueChange(event);
		}
	}

	public Object getValue() {
		return currentValue;
	}

	public void setValue(Object currentValue) {
//		this.currentValue = currentValue;
	}
}
