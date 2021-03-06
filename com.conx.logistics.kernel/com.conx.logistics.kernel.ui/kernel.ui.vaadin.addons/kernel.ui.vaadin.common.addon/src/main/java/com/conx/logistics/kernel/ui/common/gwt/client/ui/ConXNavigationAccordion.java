package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import java.util.Collection;
import java.util.HashSet;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;

public class ConXNavigationAccordion extends VerticalLayout implements ValueChangeListener {
	private static final long serialVersionUID = 3370715870286189986L;
	private static final String DEFAULT_NAME_PROPERTY_ID = "Name";

	private Accordion accordion;
	private HashSet<ValueChangeListener> navigationListeners;
	private Object currentValue;
	private Object namePropertyId;

	public ConXNavigationAccordion() {
		setSizeFull();
		setMargin(false, false, true, false);

		navigationListeners = new HashSet<ValueChangeListener>();
		namePropertyId = DEFAULT_NAME_PROPERTY_ID;
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
		
		Property nameProperty = null;
		for (Object id : ids) {
			Item currentItem = container.getItem(id);
			if (currentItem != null) {
				nameProperty = currentItem.getItemProperty(namePropertyId);
				if (nameProperty != null && nameProperty.getValue() instanceof String) {
					ConXNavigationTree tree = new ConXNavigationTree();
					tree.setNamePropertyId(namePropertyId);
					tree.setNavigationContainer(container, id);
					addCategory(tree, (String) nameProperty.getValue());
				}
			}
		}
	}

	public void setContainer(JPAContainer<?> jpaContainer) {
		Collection<?> ids = jpaContainer.rootItemIds();

		Property nameProperty = null;
		for (Object id : ids) {
			Item currentItem = jpaContainer.getItem(id);
			if (currentItem != null) {
				nameProperty = currentItem.getItemProperty(namePropertyId);
				if (nameProperty != null && nameProperty.getValue() instanceof String) {
					ConXNavigationTree tree = new ConXNavigationTree();
					tree.setNamePropertyId(namePropertyId);
					tree.setNavigationContainer(jpaContainer, id);
					addCategory(tree, (String) nameProperty.getValue());
				}
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
		currentValue = event.getProperty().getValue();
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

	public Object getNamePropertyId() {
		return namePropertyId;
	}

	public void setNamePropertyId(Object namePropertyId) {
		this.namePropertyId = namePropertyId;
	}
}
