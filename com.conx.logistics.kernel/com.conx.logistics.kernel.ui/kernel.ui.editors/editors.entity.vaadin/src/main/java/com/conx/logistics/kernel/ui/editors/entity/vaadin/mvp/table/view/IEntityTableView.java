package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.view;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.IEntityEditorComponentView;
import com.vaadin.data.Container;

public interface IEntityTableView extends IEntityEditorComponentView {
	/**
	 * Sets the vaadin container for the inner grid of this view.
	 * @param container 
	 * 		the vaadin data container for the table
	 */
	public void setContainerDataSource(Container container);
}