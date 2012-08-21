package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;

@Presenter(view = MultiLevelEntityEditorView.class)
public class MultiLevelEntityEditorPresenter extends ConfigurableBasePresenter<IMultiLevelEntityEditorView, MultiLevelEntityEditorEventBus>
implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean initialized = false;

	private EventBusManager ebm = null;

	private ConfigurablePresenterFactory presenterFactory = null;

	private ConfigurableBasePresenter<?, ? extends EventBus> headerPresenter;

	private EntityTableHeaderEventBus headerBus;

	private ConfigurableBasePresenter<?, ? extends EventBus> tablePresenter;

	private EntityTableEventBus tableBus;

	private ConfigurableBasePresenter<?, ? extends EventBus> lineEditorPresenter;

	private EntityLineEditorEventBus lineEditorBus;

	private ConfigurableBasePresenter<?, ? extends EventBus> footerPresenter;

	private EntityTableFooterEventBus footerBus;

	private MasterDetailComponent metaData;

	private EntityManager entityManager;

	private HashMap<String, Object> extraParams;

	public MultiLevelEntityEditorPresenter() {
		super();
	}

	/**
	 * EventBus callbacks
	 */
	public void onInit(EventBusManager ebm, PresenterFactory presenterFactory, MasterDetailComponent md, EntityManager em, HashMap<String,Object> extraParams) {
		try {
			this.setInitialized(true);
			this.metaData = md;
			this.entityManager = em;
			this.setEbm(ebm);
			this.extraParams = extraParams;
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
		}
	}
	
	//MultiLevelEntityEditorEventBus implementation
	public void onEntityItemEdit(EntityItem item) {
		((AbstractEntityEditorEventBus)lineEditorBus).entityItemEdit(item); 
	}
	
	@Override
	public void configure() {
		try {
			Map<String, Object> config = super.getConfig();
			this.metaData = (MasterDetailComponent)config.get("md");
			this.entityManager = (EntityManager)config.get("em");
			this.ebm = (EventBusManager)config.get("ebm");
			this.presenterFactory = (ConfigurablePresenterFactory)config.get("presenterFactory");
			this.presenterFactory.getCustomizer().getConfig().put("multiLevelEntityEditorPresenter", this);
			
			
			/**
			 * Create children presenters
			 */
			//-- Header
			headerPresenter = (ConfigurableBasePresenter)this.presenterFactory.createPresenter(EntityTableHeaderPresenter.class);
			headerBus = (EntityTableHeaderEventBus) headerPresenter.getEventBus();
			//headerBus.start(this.getEventBus(),null,null,extraParams);	

			//-- Table
			tablePresenter = (ConfigurableBasePresenter)this.presenterFactory.createPresenter(EntityTablePresenter.class);
			tableBus = (EntityTableEventBus) tablePresenter.getEventBus();
			//tableBus.start(this.getEventBus(),this.metaData,this.entityManager,extraParams);	

			//-- EntityLineEditor
			lineEditorPresenter = (ConfigurableBasePresenter)this.presenterFactory.createPresenter(EntityLineEditorPresenter.class);
			lineEditorBus = (EntityLineEditorEventBus) lineEditorPresenter.getEventBus();
			//lineEditorBus.start(this,this.getEventBus(),this.metaData,this.entityManager,extraParams);	

			//-- Footer
			footerPresenter = (ConfigurableBasePresenter)this.presenterFactory.createPresenter(EntityTableFooterPresenter.class);
			footerBus = (EntityTableFooterEventBus) footerPresenter.getEventBus();
			//footerBus.start(this.getEventBus(),this.metaData,this.entityManager,extraParams);	
			
			IMultiLevelEntityEditorView localView = this.getView();
			
			localView.init();
			localView.setHeader((Component) headerPresenter.getView());
			localView.setMaster((Component) tablePresenter.getView());
			localView.setDetail((Component) lineEditorPresenter.getView());
			localView.setFooter((Component) footerPresenter.getView());
			
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

	public EventBusManager getEbm() {
		return ebm;
	}

	public void setEbm(EventBusManager ebm) {
		this.ebm = ebm;
	}

	public MasterDetailComponent getMetaData() {
		return metaData;
	}

	public void setMetaData(MasterDetailComponent metaData) {
		this.metaData = metaData;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
