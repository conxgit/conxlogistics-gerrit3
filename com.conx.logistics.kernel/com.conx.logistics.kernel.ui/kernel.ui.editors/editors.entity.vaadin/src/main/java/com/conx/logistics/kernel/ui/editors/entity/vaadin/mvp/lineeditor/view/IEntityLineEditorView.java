package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.IEntityEditorComponentView;
import com.vaadin.ui.TabSheet;

public interface IEntityLineEditorView extends IEntityEditorComponentView {
	public TabSheet getMainLayout();
}