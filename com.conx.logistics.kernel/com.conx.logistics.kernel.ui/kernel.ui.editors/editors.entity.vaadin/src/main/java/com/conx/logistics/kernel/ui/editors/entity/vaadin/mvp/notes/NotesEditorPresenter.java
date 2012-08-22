package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.notes;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.header.EntityEditorToolStripButton;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.table.EntityGridFilterManager;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.EntityLineEditorSectionView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.IEntityLineEditorSectionView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.notes.view.INotesEditorView;
import com.conx.logistics.kernel.ui.filteredtable.gwt.client.ui.FilterTable;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
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
public class NotesEditorPresenter extends
		BasePresenter<INotesEditorView, NotesEditorEventBus>
		implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean initialized = false;
	private LineEditorComponent lineEditorSectionComponentModel;

	private EntityManager em;

	private JPAContainer entityContainer;

	private AbstractEntityEditorEventBus entityEditorEventListener;

	private Set<String> visibleFieldNames;

	public NotesEditorPresenter() {
		super();
	}

	/**
	 * EventBus callbacks
	 */
	public void onStart(AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em) {
		try {
			this.entityEditorEventListener = entityEditorEventListener;
			this.em = em;

			// - Create FileEntry container
			this.entityContainer = JPAContainerFactory.make(aec.getDataSource().getEntityType().getJavaType(), em);

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

//			this.form = new NotesForm();
//			form.setFormFieldFactory(new FieldFactory());

			// - Create upload form
//			final VerticalLayout uploadLayout = new VerticalLayout();
//			uploadLayout.setHeight("200px");
//			uploadLayout.setWidth("100%");
//			uploadLayout.setVisible(false);
//			uploadLayout.addComponent(form);

			// - Toolstrip
			EntityEditorToolStripButton newButton = new EntityEditorToolStripButton("toolstrip/img/new.png");
			newButton.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
//					uploadLayout.setVisible(!uploadLayout.isVisible());
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
//			gridLayout.addComponent(uploadLayout);
			gridLayout.setSizeFull();
			gridLayout.setExpandRatio(grid, 1.0f);

			// - Presenter: Init grid
			Set<String> nestedFieldNames = aec.getDataSource().getNestedFieldNames();
			for (String npp : nestedFieldNames) {
				entityContainer.addNestedContainerProperty(npp);
			}
			grid.setContainerDataSource(entityContainer);

			this.visibleFieldNames = aec.getDataSource().getVisibleFieldNames();
			grid.setVisibleColumns(visibleFieldNames.toArray(new String[0]));
			grid.addListener(new ItemClickListener() {
				private static final long serialVersionUID = 7230326485331772539L;
				public void itemClick(ItemClickEvent event) {
					JPAContainerItem item = (JPAContainerItem) event.getItem();
					Object entity = item.getEntity();
					entity.toString();
					NotesEditorPresenter.this.entityEditorEventListener.entityItemEdit(item);
				}
			});

			((IEntityLineEditorSectionView) getView()).getMainLayout().addComponent(gridLayout);

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
//		form.setItemDataSource(item, this.visibleFieldNames);
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
