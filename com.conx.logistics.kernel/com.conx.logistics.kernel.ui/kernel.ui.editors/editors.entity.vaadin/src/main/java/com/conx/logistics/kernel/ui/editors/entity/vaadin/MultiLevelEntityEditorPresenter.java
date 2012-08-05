package com.conx.logistics.kernel.ui.editors.entity.vaadin;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.PresenterFactory;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.footer.EntityTableFooterEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.footer.EntityTableFooterPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.view.IMultiLevelEntityEditorView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.view.MultiLevelEntityEditorView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;

@Presenter(view = MultiLevelEntityEditorView.class)
public class MultiLevelEntityEditorPresenter extends BasePresenter<IMultiLevelEntityEditorView, MultiLevelEntityEditorEventBus>
		implements Property.ValueChangeListener {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean initialized = false;
	
	private EventBusManager ebm = null;
	
	private PresenterFactory presenterFactory = null;

	private IPresenter<?, ? extends EventBus> headerPresenter;

	private EntityTableFooterEventBus headerBus;
	

	
	public MultiLevelEntityEditorPresenter() {
		super();
	}

	/**
	 * 
	 * EventBus callbacks
	 * 
	 */
	public void onStart(EventBusManager ebm, PresenterFactory presenterFactory) {
		try {
			this.initialized  = true;
			this.ebm = ebm;
			this.presenterFactory = presenterFactory;
			
			
			/**
			 * Create children presenters
			 * 
			 */
			//-- Header
			headerPresenter = this.presenterFactory.createPresenter(EntityTableFooterPresenter.class);
			headerBus = (EntityTableFooterEventBus) headerPresenter.getEventBus();
			headerBus.start(this);	
			
			//-- Table
			
			//-- EntityLineEditor
			
			//-- Footer
			
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
