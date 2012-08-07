package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.view;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.IEntityEditorComponentView;
import com.vaadin.ui.Component;

public interface IMultiLevelEntityEditorView extends IEntityEditorComponentView {
	public void setHeader(Component component);
	public void setMaster(Component component);
	public void setDetail(Component component);
	public void setFooter(Component component);
}