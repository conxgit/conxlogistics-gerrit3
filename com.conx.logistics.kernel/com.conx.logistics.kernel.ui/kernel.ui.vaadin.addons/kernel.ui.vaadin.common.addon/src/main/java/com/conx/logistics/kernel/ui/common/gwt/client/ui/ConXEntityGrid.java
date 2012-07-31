package com.conx.logistics.kernel.ui.common.gwt.client.ui;

import com.conx.logistics.kernel.ui.filteredtable.gwt.client.ui.FilterDecorator;
import com.conx.logistics.kernel.ui.filteredtable.gwt.client.ui.FilterGenerator;
import com.conx.logistics.kernel.ui.filteredtable.gwt.client.ui.FilterTable;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.VerticalLayout;

public class ConXEntityGrid extends VerticalLayout implements FilterDecorator, FilterGenerator {
	private static final long serialVersionUID = -19074647254L;
	
	private FilterTable grid;
	
	public ConXEntityGrid(Container container) {
		setSizeFull();
		grid = new FilterTable();
		grid.setSizeFull();
		grid.setContainerDataSource(container);
		grid.setFilterDecorator(this);
		grid.setFilterGenerator(this);
		grid.setFiltersVisible(true);
		setStyleName("conx-entity-grid");
		addComponent(grid);
		setExpandRatio(grid, 1.0f);
	}

	public String getEnumFilterDisplayName(Object propertyId, Object value) {
		// TODO Auto-generated method stub
		return "wooot";
	}

	public Resource getEnumFilterIcon(Object propertyId, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBooleanFilterDisplayName(Object propertyId, boolean value) {
		// TODO Auto-generated method stub
		return "BOOL";
	}

	public Resource getBooleanFilterIcon(Object propertyId, boolean value) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isTextFilterImmediate(Object propertyId) {
		// TODO Auto-generated method stub
		return true;
	}

	public int getTextChangeTimeout(Object propertyId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getFromCaption() {
		// TODO Auto-generated method stub
		return "from";
	}

	public String getToCaption() {
		// TODO Auto-generated method stub
		return "to";
	}

	public String getSetCaption() {
		// TODO Auto-generated method stub
		return "caption";
	}

	public String getClearCaption() {
		// TODO Auto-generated method stub
		return "clear";
	}

	@SuppressWarnings("serial")
	public Filter generateFilter(Object propertyId, Object value) {
		// TODO Auto-generated method stub
		return new Filter() {
			
			public boolean passesFilter(Object itemId, Item item)
					throws UnsupportedOperationException {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean appliesToProperty(Object propertyId) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}
}
