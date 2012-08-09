package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.table.EntityGridFilterManager;
import com.conx.logistics.kernel.ui.filteredtable.gwt.client.ui.FilterTable;
import com.vaadin.data.Container;
import com.vaadin.ui.VerticalLayout;

public class EntityTableView extends VerticalLayout implements IEntityTableView {
	private static final long serialVersionUID = 1L;

	@UiField
	VerticalLayout mainLayout;
	
	private FilterTable grid;
	private EntityGridFilterManager gridManager;
	
	public EntityTableView() {
		setSizeFull();
	}
	
	@Override
	public void setContainerDataSource(Container container) {
		grid.setContainerDataSource(container);
	}

	@Override
	public void init() {
		if (mainLayout != null) {
			gridManager = new EntityGridFilterManager();
			
			grid = new FilterTable();
			grid.setSizeFull();
			grid.setSelectable(true);
			grid.setFilterDecorator(gridManager);
			grid.setFilterGenerator(gridManager);
			grid.setFiltersVisible(true);
			
			//-- Set container
			
			mainLayout.setStyleName("conx-entity-grid");
			mainLayout.addComponent(grid);
			mainLayout.setExpandRatio(grid, 1.0f);
		}
	}

	@Override
	public FilterTable getTable() {
		return this.grid;
	}
}
