package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.view.EntityTableView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.view.IEntityTableView;
import com.conx.logistics.mdm.domain.task.TaskDefinition;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

@Presenter(view = EntityTableView.class)
public class EntityTablePresenter extends BasePresenter<IEntityTableView, EntityTableEventBus>
		implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean initialized = false;
	
	private MultiLevelEntityEditorPresenter parentPresenter;

	private EntityManager kernelSystemEntityManager;

	private EntityManager em;

	private DataSource dataSource;

	private Class javaEntityClass;

	private JPAContainer entityContainer;

	public EntityTablePresenter() {
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
	public void onStart(AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em) {
		try {
			this.em = em;
			this.dataSource = ((MasterDetailComponent)aec).getDataSource();
			this.javaEntityClass = this.dataSource.getEntityType().getJavaType();
			
			//-- Instanciate View and Table
			this.getView().init();
			
			//-- Create datasource/container from md.dataSource
			this.entityContainer = JPAContainerFactory.make(this.javaEntityClass,this.em);
			Set<String> nestedFieldNames = this.dataSource.getNestedFieldNames();
			for (String npp : nestedFieldNames)
			{
				this.entityContainer.addNestedContainerProperty(npp);
			}
			getView().getTable().setContainerDataSource(this.entityContainer);			
			
			String[] visibleFieldNames = this.dataSource.getVisibleFieldNames().toArray(new String[0]);
			getView().getTable().setVisibleColumns(visibleFieldNames);
			getView().getTable().addListener(new ItemClickListener() {
				private static final long serialVersionUID = 7230326485331772539L;

				public void itemClick(ItemClickEvent event) {
					JPAContainerItem item = (JPAContainerItem)event.getItem();
					Object entity = item.getEntity();
					entity.toString();
					MultiLevelEntityEditorEventBus eb = EntityTablePresenter.this.parentPresenter.getEventBus();
					eb.entityItemEdit(item);
				}
			});
			
			//-- Done
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
