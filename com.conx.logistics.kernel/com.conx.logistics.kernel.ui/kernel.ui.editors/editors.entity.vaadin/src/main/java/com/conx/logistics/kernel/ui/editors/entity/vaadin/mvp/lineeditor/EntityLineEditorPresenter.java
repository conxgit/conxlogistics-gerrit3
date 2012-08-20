package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.attachment.AttachmentEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.note.NoteEditorComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header.EntityTableHeaderEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header.EntityTableHeaderPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.EntityLineEditorView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.IEntityLineEditorSectionView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.IEntityLineEditorView;
import com.google.gwt.dom.client.LIElement;
import com.vaadin.addon.jpacontainer.EntityItem;
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
	private List<IPresenter<?, ? extends EventBus>> editorSections = new ArrayList<IPresenter<?, ? extends EventBus>>();

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
	public void onStart(MultiLevelEntityEditorPresenter parentPresenter, AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em) {
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
			AbstractEntityEditorEventBus elesb = null;
			Component attachmentsComponent = null;
			Component notesComponent = null;
			for (LineEditorComponent lec : lecs)
			{
				elesp = this.parentPresenter.getPresenterFactory().createPresenter(EntityLineEditorSectionPresenter.class);
				elesb = (AbstractEntityEditorEventBus) elesp.getEventBus();
				((EntityLineEditorSectionEventBus)elesb).start((EntityLineEditorPresenter)this,(AbstractEntityEditorEventBus)this.parentPresenter.getEventBus(),(AbstractConXComponent)lec,this.parentPresenter.getEntityManager());
				editorSections.add(elesp);
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

	//MultiLevelEntityEditorEventBus implementation
	public void onEntityItemEdit(EntityItem item) {
		for (IPresenter<?, ? extends EventBus>  elesp : editorSections)
		{
			((AbstractEntityEditorEventBus)elesp.getEventBus()).entityItemEdit(item);
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
