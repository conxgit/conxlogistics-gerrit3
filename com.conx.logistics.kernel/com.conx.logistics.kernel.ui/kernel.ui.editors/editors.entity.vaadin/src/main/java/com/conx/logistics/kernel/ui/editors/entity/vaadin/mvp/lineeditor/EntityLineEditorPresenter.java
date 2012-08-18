package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.ui.components.domain.attachment.AttachmentEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.note.NoteEditorComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header.EntityTableHeaderEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header.EntityTableHeaderPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.EntityLineEditorView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.IEntityLineEditorSectionView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.IEntityLineEditorView;
import com.google.gwt.dom.client.LIElement;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;

@Presenter(view = EntityLineEditorView.class)
public class EntityLineEditorPresenter extends BasePresenter<IEntityLineEditorView, EntityLineEditorEventBus>
		implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean initialized = false;
	private MultiLevelEntityEditorPresenter parentPresenter;
	private List<EntityLineEditorSectionPresenter> editorSections = new ArrayList<EntityLineEditorSectionPresenter>();

	public EntityLineEditorPresenter() {
		super();
	}

	public MultiLevelEntityEditorPresenter getParentPresenter() {
		return parentPresenter;
	}

	public void setParentPresenter(MultiLevelEntityEditorPresenter parentPresenter) {
		this.parentPresenter = parentPresenter;
	}


	/**
	 * EventBus callbacks
	 */
	public void onStart(MultiLevelEntityEditorPresenter parentPresenter) {
		try {
			this.parentPresenter = parentPresenter;
			
			this.getView().init();
			
			/**
			 * 1. Get LineEditor models
			 * 
			 * 2. For each, create LineEditorSection presenters
			 */
			
			//1. 
			Set<LineEditorComponent> lecs = this.parentPresenter.getMetaData().getLineEditorPanel().getLineEditors();
			
			//2. 
			IPresenter<?, ? extends EventBus>  elesp = null;
			EntityLineEditorSectionEventBus elesb = null;
			Component attachmentsComponent = null;
			Component notesComponent = null;
			for (LineEditorComponent lec : lecs)
			{
				elesp = this.parentPresenter.getPresenterFactory().createPresenter(EntityLineEditorSectionPresenter.class);
				elesb = (EntityLineEditorSectionEventBus) elesp.getEventBus();
				elesb.start(this,lec);
				if (lec.getContent() instanceof AttachmentEditorComponent)
					attachmentsComponent = (Component)elesp.getView();
				else if (lec.getContent() instanceof NoteEditorComponent)
					notesComponent = (Component)elesp.getView();
				else //Other: simply add lec
				{
					//TODO:
				}
			}
			
			
			//- Attachments
			if (attachmentsComponent != null)
				((IEntityLineEditorView)getView()).getMainLayout().addTab(attachmentsComponent,"Attachements");

			//- Notes
			if (notesComponent != null)
				((IEntityLineEditorView)getView()).getMainLayout().addTab(notesComponent,"Notes");
							
			
			this.setInitialized(true);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
		}
	}
	
	@Override
	public void bind() {
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
}
