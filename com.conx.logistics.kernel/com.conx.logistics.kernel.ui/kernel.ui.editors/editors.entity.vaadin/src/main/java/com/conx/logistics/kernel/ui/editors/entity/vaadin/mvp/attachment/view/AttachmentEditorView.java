package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.attachment.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class AttachmentEditorView extends VerticalLayout implements IAttachmentEditorView {
	private static final long serialVersionUID = 1L;
	
	@UiField
	private VerticalLayout mainLayout;
	
	public AttachmentEditorView() {
		setSizeFull();
	}

	@Override
	public void init() {
	}

	public Layout getMainLayout() {
		return mainLayout;
	}
}
