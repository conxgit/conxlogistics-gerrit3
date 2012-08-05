package com.conx.logistics.kernel.ui.editors.entity.vaadin.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.conx.logistics.kernel.ui.common.gwt.client.ui.ConXFooter;
import com.conx.logistics.kernel.ui.common.gwt.client.ui.ConXHeader;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MultiLevelEntityEditorView extends Window implements IMultiLevelEntityEditorView {
	private static final long serialVersionUID = 6734894019724150200L;

	@UiField
	VerticalLayout mainLayout;
	  
	private ConXHeader headerStrip;
	private ConXFooter footerStrip;
	private TabSheet applicationTabSheet;
	
	@Override
	public VerticalLayout getMainLayout() {
		return mainLayout;
	}
	
	@Override
	public void detach() {
		super.detach();
	}

	public MultiLevelEntityEditorView() {
	}
}
