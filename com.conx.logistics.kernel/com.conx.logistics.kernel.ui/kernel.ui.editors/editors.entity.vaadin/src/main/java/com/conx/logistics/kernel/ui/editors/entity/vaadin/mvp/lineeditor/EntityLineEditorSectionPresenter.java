package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.attachment.AttachmentEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.note.NoteEditorComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.header.EntityEditorToolStripButton;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.table.EntityGridFilterManager;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.upload.AttachmentForm;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.upload.FileUploadPanel;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.attachment.AttachmentEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.attachment.AttachmentEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.EntityLineEditorSectionView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.IEntityLineEditorSectionView;
import com.conx.logistics.kernel.ui.filteredtable.gwt.client.ui.FilterTable;
import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Presenter(view = EntityLineEditorSectionView.class)
public class EntityLineEditorSectionPresenter extends BasePresenter<IEntityLineEditorSectionView, EntityLineEditorSectionEventBus>
		implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean initialized = false;
	private EntityLineEditorPresenter parentPresenter;

	private LineEditorComponent lineEditorSectionComponentModel;

	private AbstractEntityEditorEventBus eventBus;

	public EntityLineEditorSectionPresenter() {
		super();
	}

	/**
	 * EventBus callbacks
	 */
	public void onStart(EntityLineEditorPresenter parentPresenter, AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em) {
		try {
			this.lineEditorSectionComponentModel = (LineEditorComponent)aec;
			this.parentPresenter = parentPresenter;
			
			//1. Get comp model
			AbstractConXComponent lecm = this.lineEditorSectionComponentModel.getContent();
			
			//2. Based on type, auto create editor
			Component leComponent = null;
			if (lecm instanceof AttachmentEditorComponent)
			{
				IPresenter<?, ? extends EventBus> aep = this.parentPresenter.getParentPresenter().getPresenterFactory().createPresenter(AttachmentEditorPresenter.class);
				this.eventBus = (AttachmentEditorEventBus) aep.getEventBus();
				eventBus.start((AbstractEntityEditorEventBus)parentPresenter.getParentPresenter().getEventBus(),(AbstractConXComponent)lecm,parentPresenter.getParentPresenter().getEntityManager());				
				leComponent = (Component)aep.getView();
			}
			else if (lecm instanceof NoteEditorComponent)
			{
				//TODO:
				leComponent = new VerticalLayout();
			}
			else
			{
				//TODO:
				leComponent = new VerticalLayout();
			}
			
			((IEntityLineEditorSectionView)getView()).getMainLayout().addComponent(leComponent);
			
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
		this.eventBus.entityItemEdit(item);
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
