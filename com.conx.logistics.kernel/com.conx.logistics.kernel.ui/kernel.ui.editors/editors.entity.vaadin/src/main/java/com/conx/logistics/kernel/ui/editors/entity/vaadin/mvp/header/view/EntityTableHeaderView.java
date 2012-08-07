package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header.view;

import org.vaadin.mvp.uibinder.annotation.UiField;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.header.EntityEditorToolStripButton;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class EntityTableHeaderView extends VerticalLayout implements IEntityTableHeaderView {
	private static final long serialVersionUID = 1L;
	
	private static final String TOOLSTRIP_IMG_DELETE_PNG = "toolstrip/img/delete.png";
	private static final String TOOLSTRIP_IMG_SAVE_PNG = "toolstrip/img/save.png";
	private static final String TOOLSTRIP_IMG_EDIT_PNG = "toolstrip/img/edit.png";
	private static final String TOOLSTRIP_IMG_NEW_PNG = "toolstrip/img/new.png";

	@UiField
	HorizontalLayout mainLayout;
	@UiField
	HorizontalLayout leftLayout;
	@UiField
	HorizontalLayout rightLayout;
	
	private EntityEditorToolStripButton newButton;
	private EntityEditorToolStripButton editButton;
	private EntityEditorToolStripButton saveButton;
	private EntityEditorToolStripButton deleteButton;

	public EntityTableHeaderView() {
		setSizeFull();
	}
	
	@Override
	public void init() {
		newButton = new EntityEditorToolStripButton(TOOLSTRIP_IMG_NEW_PNG);
		editButton = new EntityEditorToolStripButton(TOOLSTRIP_IMG_EDIT_PNG);
		saveButton = new EntityEditorToolStripButton(TOOLSTRIP_IMG_SAVE_PNG);
		deleteButton = new EntityEditorToolStripButton(TOOLSTRIP_IMG_DELETE_PNG);
		
		leftLayout.setSpacing(true);
		leftLayout.addComponent(newButton);
		leftLayout.addComponent(editButton);
		leftLayout.addComponent(saveButton);
		leftLayout.addComponent(deleteButton);
		
		mainLayout.setComponentAlignment(leftLayout, Alignment.MIDDLE_LEFT);
		mainLayout.setComponentAlignment(rightLayout, Alignment.MIDDLE_RIGHT);
	}
}
