package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class EntityLineEditorSectionView extends VerticalLayout implements IEntityLineEditorSectionView {
	private static final long serialVersionUID = 1L;
	
	@UiField
	private VerticalLayout mainLayout;
	
	public EntityLineEditorSectionView() {
		setSizeFull();
	}

	@Override
	public void init() {
	}

	public Layout getMainLayout() {
		return mainLayout;
	}
}
