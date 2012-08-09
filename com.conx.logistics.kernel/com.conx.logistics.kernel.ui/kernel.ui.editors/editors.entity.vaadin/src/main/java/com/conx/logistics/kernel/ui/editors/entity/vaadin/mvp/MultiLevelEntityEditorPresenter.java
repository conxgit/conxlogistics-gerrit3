package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.PresenterFactory;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.footer.EntityTableFooterEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.footer.EntityTableFooterPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header.EntityTableHeaderEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header.EntityTableHeaderPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.EntityLineEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.EntityLineEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.EntityTableEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.EntityTablePresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.view.IMultiLevelEntityEditorView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.view.MultiLevelEntityEditorView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;

@Presenter(view = MultiLevelEntityEditorView.class)
public class MultiLevelEntityEditorPresenter extends BasePresenter<IMultiLevelEntityEditorView, MultiLevelEntityEditorEventBus>
implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean initialized = false;

	private EventBusManager ebm = null;

	private PresenterFactory presenterFactory = null;

	private IPresenter<?, ? extends EventBus> headerPresenter;

	private EntityTableHeaderEventBus headerBus;

	private IPresenter<?, ? extends EventBus> tablePresenter;

	private EntityTableEventBus tableBus;

	private IPresenter<?, ? extends EventBus> lineEditorPresenter;

	private EntityLineEditorEventBus lineEditorBus;

	private IPresenter<?, ? extends EventBus> footerPresenter;

	private EntityTableFooterEventBus footerBus;

	public MultiLevelEntityEditorPresenter() {
		super();
	}

	/**
	 * EventBus callbacks
	 */
	public void onStart(EventBusManager ebm, PresenterFactory presenterFactory, MasterDetailComponent md, EntityManager em) {
		try {
			this.setInitialized(true);
			this.setEbm(ebm);
			this.presenterFactory = presenterFactory;
			
			/**
			 * Create children presenters
			 */
			//-- Header
			headerPresenter = this.presenterFactory.createPresenter(EntityTableHeaderPresenter.class);
			headerBus = (EntityTableHeaderEventBus) headerPresenter.getEventBus();
			headerBus.start(this);	

			//-- Table
			tablePresenter = this.presenterFactory.createPresenter(EntityTablePresenter.class);
			tableBus = (EntityTableEventBus) tablePresenter.getEventBus();
			tableBus.start(this,md,em);	

			//-- EntityLineEditor
			lineEditorPresenter = this.presenterFactory.createPresenter(EntityLineEditorPresenter.class);
			lineEditorBus = (EntityLineEditorEventBus) lineEditorPresenter.getEventBus();
			lineEditorBus.start(this);	

			//-- Footer
			footerPresenter = this.presenterFactory.createPresenter(EntityTableFooterPresenter.class);
			footerBus = (EntityTableFooterEventBus) footerPresenter.getEventBus();
			footerBus.start(this);	
			
			IMultiLevelEntityEditorView localView = this.getView();
			
			localView.init();
			localView.setHeader((Component) headerPresenter.getView());
			localView.setMaster((Component) tablePresenter.getView());
			localView.setDetail((Component) lineEditorPresenter.getView());
			localView.setFooter((Component) footerPresenter.getView());
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

	public EventBusManager getEbm() {
		return ebm;
	}

	public void setEbm(EventBusManager ebm) {
		this.ebm = ebm;
	}
}
