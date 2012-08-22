package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.notes.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class NotesEditorView extends VerticalLayout implements INotesEditorView {
	private static final long serialVersionUID = 1L;
	
	@UiField
	private VerticalLayout mainLayout;
	
	public NotesEditorView() {
		setSizeFull();
	}

	@Override
	public void init() {
	}

	public Layout getMainLayout() {
		return mainLayout;
	}
}
