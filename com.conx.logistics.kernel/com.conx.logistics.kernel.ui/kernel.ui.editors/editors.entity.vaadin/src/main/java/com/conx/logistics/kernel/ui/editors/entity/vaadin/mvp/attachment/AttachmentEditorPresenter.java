package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.attachment;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.documentlibrary.remote.services.IRemoteDocumentRepository;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.attachment.AttachmentEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.fieldfactory.ConXFieldFactory;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.header.EntityEditorToolStripButton;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.table.EntityGridFilterManager;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.upload.AttachmentForm;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.ConfigurableBasePresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.attachment.view.IAttachmentEditorView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.EntityLineEditorSectionView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.IEntityLineEditorSectionView;
import com.conx.logistics.kernel.ui.filteredtable.gwt.client.ui.FilterTable;
import com.conx.logistics.mdm.domain.BaseEntity;
import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;
import com.conx.logistics.mdm.domain.documentlibrary.Folder;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.util.DefaultQueryModifierDelegate;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@Presenter(view = EntityLineEditorSectionView.class)
public class AttachmentEditorPresenter extends
ConfigurableBasePresenter<IAttachmentEditorView, AttachmentEditorEventBus>
		implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean initialized = false;
	private LineEditorComponent lineEditorSectionComponentModel;

	private JPAContainer entityContainer;

	private AbstractEntityEditorEventBus entityEditorEventListener;

	private AttachmentForm form;

	private Set<String> visibleFieldNames;

	private IRemoteDocumentRepository docRepo;

	private String folderId;

	private Folder docFolder;

	private VerticalLayout defaultPanel;

	private AbstractConXComponent attachmentComponent;

	private EntityManager entityManager;

	private HashMap<String, Object> extraInitParams;

	public AttachmentEditorPresenter() {
		super();
	}

	/**
	 * EventBus callbacks
	 */
	public void onStart(AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em, HashMap<String,Object> extraParams) {
		try {

			this.entityEditorEventListener = entityEditorEventListener;
			this.attachmentComponent = aec;
			this.entityManager = em;
			this.extraInitParams = extraParams;			
			
			
			this.defaultPanel = new VerticalLayout();
			this.defaultPanel.setCaption("Select Record");
			resetViewContentPanel(this.defaultPanel);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
		}
	}

	private void resetViewContentPanel(VerticalLayout panel) {
		((IEntityLineEditorSectionView) getView()).getMainLayout().removeAllComponents();
		((IEntityLineEditorSectionView) getView()).getMainLayout().addComponent(panel);
	}

	private void initialize() throws ClassNotFoundException {

	}
	
	//MultiLevelEntityEditorEventBus implementation
	public void onEntityItemEdit(EntityItem item) {
		BaseEntity entity = (BaseEntity)item.getEntity();
		this.docFolder = entity.getDocFolder();
		if (!isInitialized())
		{
			try {
				initialize();
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String stacktrace = sw.toString();
				logger.error(stacktrace);
			}
		}
		this.entityContainer.getEntityProvider().setQueryModifierDelegate(
		        new DefaultQueryModifierDelegate () {
		            @Override
		            public void filtersWillBeAdded(
		                    CriteriaBuilder criteriaBuilder,
		                    CriteriaQuery<?> query,
		                    List<Predicate> predicates) {
		                Root<?> fromFileEntry = query.getRoots().iterator().next();

		                // Add a "WHERE age > 116" expression
		                Path<Folder> parentFolder = fromFileEntry.<Folder>get("folder");
		                predicates.add(criteriaBuilder.equal(parentFolder, AttachmentEditorPresenter.this.docFolder));
		            }});
		this.form.setDocFolder(this.docFolder);
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

	@Override
	public void configure() {
		this.docRepo = (IRemoteDocumentRepository)getConfig().get("iremoteDocumentRepository");
		this.attachmentComponent = (AttachmentEditorComponent)getConfig().get("componentModel");
		this.entityManager = (EntityManager)getConfig().get("em");

		// - Create FileEntry container
		this.entityContainer = JPAContainerFactory.make(FileEntry.class, entityManager);

		// - View: Create attachment grid and layout
		EntityGridFilterManager gridManager = new EntityGridFilterManager();
		FilterTable grid = new FilterTable();
		grid.setSizeFull();
		grid.setSelectable(true);
		grid.setFilterDecorator(gridManager);
		grid.setFilterGenerator(gridManager);
		grid.setFiltersVisible(true);
		// grid.setEditable(true);
		// grid.setTableFieldFactory(new FieldFactory());

		this.form = new AttachmentForm(this.docRepo);
		form.setFormFieldFactory(new ConXFieldFactory());

		// - Create upload form
		final VerticalLayout uploadLayout = new VerticalLayout();
		uploadLayout.setHeight("200px");
		uploadLayout.setWidth("100%");
		uploadLayout.setVisible(false);
		uploadLayout.addComponent(form);

		// - Toolstrip
		EntityEditorToolStripButton newButton = new EntityEditorToolStripButton("toolstrip/img/new.png");
		newButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				uploadLayout.setVisible(!uploadLayout.isVisible());
			}
		});
		EntityEditorToolStripButton deleteButton = new EntityEditorToolStripButton("toolstrip/img/delete.png");

		HorizontalLayout innerToolStrip1 = new HorizontalLayout();
		innerToolStrip1.setSpacing(true);
		innerToolStrip1.addComponent(newButton);
		innerToolStrip1.addComponent(deleteButton);

		HorizontalLayout toolStrip1 = new HorizontalLayout();
		toolStrip1.setHeight("40px");
		toolStrip1.setWidth("100%");
		toolStrip1.setStyleName("conx-entity-toolstrip");
		toolStrip1.addComponent(innerToolStrip1);

		toolStrip1.setComponentAlignment(innerToolStrip1,
				Alignment.MIDDLE_LEFT);

		VerticalLayout gridLayout = new VerticalLayout();
		gridLayout.setStyleName("conx-entity-grid");
		gridLayout.addComponent(toolStrip1);
		gridLayout.addComponent(grid);
		gridLayout.addComponent(uploadLayout);
		gridLayout.setSizeFull();
		gridLayout.setExpandRatio(grid, 1.0f);

		// - Presenter: Init grid
		Set<String> nestedFieldNames = attachmentComponent.getDataSource().getNestedFieldNames();
		for (String npp : nestedFieldNames) {
			entityContainer.addNestedContainerProperty(npp);
		}
		grid.setContainerDataSource(entityContainer);

		this.visibleFieldNames = attachmentComponent.getDataSource().getVisibleFieldNames();
		final HashSet<String> formVisibleFieldNames = new HashSet<String>();
		formVisibleFieldNames.addAll(this.visibleFieldNames);
		
		formVisibleFieldNames.remove("docType.code");
		formVisibleFieldNames.add("docType");
		grid.setVisibleColumns(visibleFieldNames.toArray(new String[0]));
		grid.addListener(new ItemClickListener() {
			private static final long serialVersionUID = 7230326485331772539L;
			public void itemClick(ItemClickEvent event) {
				JPAContainerItem item = (JPAContainerItem) event.getItem();
				Object entity = item.getEntity();
				entity.toString();
				form.setItemDataSource(item,formVisibleFieldNames);
				//AttachmentEditorPresenter.this.entityEditorEventListener.entityItemEdit(item);
			}
		});


		resetViewContentPanel(gridLayout);
		this.setInitialized(true);
	}
}
