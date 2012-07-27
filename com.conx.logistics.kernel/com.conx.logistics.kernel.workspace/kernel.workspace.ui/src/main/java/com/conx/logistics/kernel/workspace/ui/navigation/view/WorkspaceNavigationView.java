package com.conx.logistics.kernel.workspace.ui.navigation.view;

import org.vaadin.mvp.uibinder.IUiBindable;
import org.vaadin.mvp.uibinder.annotation.UiField;

import com.vaadin.ui.VerticalLayout;

public class WorkspaceNavigationView extends VerticalLayout implements IWorkspaceNavigationView, IUiBindable {
	private static final long serialVersionUID = 392877101L;
	
	@UiField
	VerticalLayout mainLayout;

	@Override
	public VerticalLayout getMainLayout() {
		return this.mainLayout;
	}

}
