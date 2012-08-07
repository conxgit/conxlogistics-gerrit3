package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class EntityLineEditorView extends VerticalLayout implements IEntityLineEditorView {
	private static final long serialVersionUID = 1L;
	
	@UiField
	TabSheet mainLayout;
	
	public EntityLineEditorView() {
		setSizeFull();
	}

	@Override
	public void init() {
		if (mainLayout != null) {
			mainLayout.addTab(new VerticalLayout(), "TEST");
		}
	}
}
