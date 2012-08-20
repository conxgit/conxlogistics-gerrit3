package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class MultiLevelEntityEditorView extends VerticalLayout implements IMultiLevelEntityEditorView {
	private static final long serialVersionUID = 1L;
	
	@UiField
	VerticalLayout mainLayout;
	@UiField
	VerticalLayout headerLayout;
	@UiField
	VerticalLayout contentLayout;

	private VerticalSplitPanel splitPanel;

	public MultiLevelEntityEditorView() {
		setSizeFull();
	}

	public void init() {
		if (contentLayout != null) {
			splitPanel = new VerticalSplitPanel();
			splitPanel.setSizeFull();
			splitPanel.setStyleName("conx-entity-editor");
			contentLayout.addComponent(splitPanel);
			mainLayout.setExpandRatio(contentLayout, 1.0f);
		}
	}

	@Override
	public void setHeader(Component component) {
		headerLayout.removeAllComponents();
		headerLayout.addComponent(component);
	}

	@Override
	public void setMaster(Component component) {
		splitPanel.setFirstComponent(component);
	}

	@Override
	public void setDetail(Component component) {
		splitPanel.setSecondComponent(component);
	}

	@Override
	public void setFooter(Component component) {
	}
}
