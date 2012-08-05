package com.conx.logistics.kernel.ui.editors.entity.vaadin.footer.view;

import com.vaadin.ui.VerticalLayout;

public interface IEntityTableFooterView {
	/**
	 * Gets the outermost layout for the multi level entity editor
	 * @return Outermost layout for main window
	 */
	public abstract VerticalLayout getMainLayout();
}