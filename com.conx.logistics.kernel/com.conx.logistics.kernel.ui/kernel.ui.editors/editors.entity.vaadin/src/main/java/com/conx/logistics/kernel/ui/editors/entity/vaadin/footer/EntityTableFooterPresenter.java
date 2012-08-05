package com.conx.logistics.kernel.ui.editors.entity.vaadin.footer;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.footer.view.EntityTableFooterView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.footer.view.IEntityTableFooterView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;

@Presenter(view = EntityTableFooterView.class)
public class EntityTableFooterPresenter extends BasePresenter<IEntityTableFooterView, EntityTableFooterEventBus>
		implements Property.ValueChangeListener {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean initialized = false;
	
	private MultiLevelEntityEditorPresenter parentPresenter;

	public EntityTableFooterPresenter() {
		super();
	}

	public MultiLevelEntityEditorPresenter getParentPresenter() {
		return parentPresenter;
	}

	public void setParentPresenter(MultiLevelEntityEditorPresenter parentPresenter) {
		this.parentPresenter = parentPresenter;
	}


	/**
	 * 
	 * EventBus callbacks
	 * 
	 */
	public void onStart(MultiLevelEntityEditorPresenter parentPresenter) {
		try {
			this.initialized  = true;
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
		// TODO Auto-generated method stub
		
	}
}
