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
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.view.EntityTableView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.view.IEntityTableView;
import com.conx.logistics.mdm.domain.task.TaskDefinition;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;

@Presenter(view = EntityTableView.class)
public class EntityTablePresenter extends BasePresenter<IEntityTableView, EntityTableEventBus>
		implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean initialized = false;
	
	private MultiLevelEntityEditorPresenter parentPresenter;

	private EntityManager kernelSystemEntityManager;

	private EntityManager em;

	private Object entityContainer;

	private DataSource dataSource;

	private Class javaEntityClass;

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
	public void onStart(MultiLevelEntityEditorPresenter parentPresenter, MasterDetailComponent md, EntityManager em) {
		try {
			this.em = em;
			this.dataSource = md.getDataSource();
			this.javaEntityClass = this.dataSource.getEntityType().getJavaType();
			
			//-- Instanciate View and Table
			this.getView().init();
			
			//-- Create datasource/container from md.dataSource
			this.entityContainer = JPAContainerFactory.make(this.javaEntityClass,this.em);
			String[] visibleFieldNames = this.dataSource.getVisibleFieldNames().toArray(new String[0]);
			getView().getTable().setVisibleColumns(visibleFieldNames);
			
			
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
