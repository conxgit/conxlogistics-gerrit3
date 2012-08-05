package com.conx.logistics.kernel.ui.editors.entity.vaadin.lineeditor.view;

import com.vaadin.ui.VerticalLayout;

public interface IEntityLineEditorView {
	/**
	 * Gets the outermost layout for the multi level entity editor
	 * @return Outermost layout for main window
	 */
	public abstract VerticalLayout getMainLayout();
}