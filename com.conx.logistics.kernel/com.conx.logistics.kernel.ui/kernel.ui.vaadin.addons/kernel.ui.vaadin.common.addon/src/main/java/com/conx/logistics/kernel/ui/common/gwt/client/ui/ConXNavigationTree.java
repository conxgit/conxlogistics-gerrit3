package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.TreeTable;

public class ConXNavigationTree extends TreeTable implements ValueChangeListener {
	private static final long serialVersionUID = -4744167266095653799L;

	private static final String DEFAULT_PARENT_ICON_URL = "icons/conx/conx-navigation-warehouse-icon.png";
	private static final String DEFAULT_CHILD_ICON_URL = "icons/conx/conx-navigation-warehouse-icon.png";

	private ArrayList<ConXNavigationTreeItem> itemList;
	private ArrayList<Object> valueList;
	private ValueChangeListener navigationListener;

	public ConXNavigationTree() {
		itemList = new ArrayList<ConXNavigationTreeItem>();
		valueList = new ArrayList<Object>();

		setSizeFull();
		setSelectable(true);
		setImmediate(true);
		addStyleName("conx-tree-nav");
		setColumnHeaderMode(ConXNavigationTree.COLUMN_HEADER_MODE_HIDDEN);
		addContainerProperty("name", ConXNavigationTreeItem.class, "");
	}

	public ConXNavigationTreeItem addConXNavigationTreeItem(Object value, String caption, String iconUrl) {
		ConXNavigationTreeItem newItem = new ConXNavigationTreeItem(caption, iconUrl, this, itemList.size());
		itemList.add(newItem);
		valueList.add(value);
		addItem(new Object[] { newItem }, newItem.getId());
		setChildrenAllowed(newItem.getId(), false);
		return newItem;
	}

	public ConXNavigationTreeItem addConXNavigationTreeItem(Object value, String caption, String iconUrl, int parentId) {
		ConXNavigationTreeItem newItem = addConXNavigationTreeItem(value, caption, iconUrl);
		if (itemList.get(parentId) != null) {
			setChildrenAllowed(parentId, true);
			setParent(newItem.getId(), parentId);
			setCollapsed(parentId, false);
		}
		return newItem;
	}

	public void setNavigationContainer(HierarchicalContainer container, Object captionPropertyId, Object parentId) {
		addChildren(container, parentId, null, captionPropertyId);
	}

	private void addChildren(HierarchicalContainer container, Object parentId, Integer parentNavItemId, Object captionPropertyId) {
		Collection<?> ids = null;
		if (parentId == null) {
			ids = container.rootItemIds();
		} else {
			ids = container.getChildren(parentId);
		}

		Item item = null;
		Object caption = null;
		for (Object id : ids) {
			item = container.getItem(id);
			caption = item.getItemProperty(captionPropertyId).getValue();
			if (caption instanceof String) {
				if (container.hasChildren(id)) {
					ConXNavigationTreeItem treeItem = null;
					if (parentNavItemId == null) {
						treeItem = addConXNavigationTreeItem(id, (String) caption, DEFAULT_PARENT_ICON_URL);
					} else {
						treeItem = addConXNavigationTreeItem(id, (String) caption, DEFAULT_PARENT_ICON_URL, parentNavItemId);
					}
					addChildren(container, id, treeItem.getId(), captionPropertyId);
				} else {
					if (parentNavItemId == null) {
						addConXNavigationTreeItem(id, (String) caption, DEFAULT_CHILD_ICON_URL);
					} else {
						addConXNavigationTreeItem(id, (String) caption, DEFAULT_CHILD_ICON_URL, parentNavItemId);
					}
				}
			}
		}
	}

	public ValueChangeListener getNavigationListener() {
		return navigationListener;
	}

	public void setNavigationListener(ValueChangeListener navigationListener) {
		this.navigationListener = navigationListener;
	}

	public void valueChange(final ValueChangeEvent event) {
		if (event.getProperty().getValue() != null) {
			if (navigationListener != null) {
				navigationListener.valueChange(new Property.ValueChangeEvent() {
					private static final long serialVersionUID = 982374932L;

					public Property getProperty() {
						return new Property() {
							private static final long serialVersionUID = -103948478L;

							public Object getValue() {
								Integer id = (Integer) event.getProperty().getValue();
								if (id != null) {
									return valueList.get((Integer) event.getProperty().getValue());
								} else {
									return null;
								}
							}

							public void setValue(Object newValue)
									throws ReadOnlyException,
									ConversionException {
							}

							public Class<?> getType() {
								Integer id = (Integer) event.getProperty().getValue();
								if (id != null) {
									return valueList.get((Integer) event.getProperty().getValue()).getClass();
								} else {
									return null;
								}
							}

							public boolean isReadOnly() {
								return false;
							}

							public void setReadOnly(boolean newStatus) {
							}

						};
					}
				});
			}
		}
	}
}
