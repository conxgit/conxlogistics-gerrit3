package com.conx.logistics.kernel.ui.editors.vaadin.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.PresenterFactory;

import com.conx.logistics.app.whse.dao.services.IDockTypeDAOService;
import com.conx.logistics.app.whse.dao.services.IWarehouseDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNDropOffDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNPickupDAOService;
import com.conx.logistics.app.whse.rcv.rcv.dao.services.IReceiveDAOService;
import com.conx.logistics.app.whse.rcv.rcv.domain.Receive;
import com.conx.logistics.data.uat.sprint2.data.TestDataManager;
import com.conx.logistics.data.uat.sprint2.data.UIComponentModelData;
import com.conx.logistics.kernel.datasource.dao.services.IDataSourceDAOService;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.ui.components.dao.services.IComponentDAOService;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorContainerComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.view.IMultiLevelEntityEditorView;
import com.conx.logistics.kernel.ui.editors.vaadin.tests.MockApp.SpringContextHelper;
import com.conx.logistics.mdm.dao.services.IAddressDAOService;
import com.conx.logistics.mdm.dao.services.IContactDAOService;
import com.conx.logistics.mdm.dao.services.ICountryDAOService;
import com.conx.logistics.mdm.dao.services.ICountryStateDAOService;
import com.conx.logistics.mdm.dao.services.IEntityMetadataDAOService;
import com.conx.logistics.mdm.dao.services.IOrganizationDAOService;
import com.conx.logistics.mdm.dao.services.IUnlocoDAOService;
import com.conx.logistics.mdm.dao.services.currency.ICurrencyUnitDAOService;
import com.conx.logistics.mdm.dao.services.documentlibrary.IDocTypeDAOService;
import com.conx.logistics.mdm.dao.services.product.IDimUnitDAOService;
import com.conx.logistics.mdm.dao.services.product.IPackUnitDAOService;
import com.conx.logistics.mdm.dao.services.product.IProductDAOService;
import com.conx.logistics.mdm.dao.services.product.IProductTypeDAOService;
import com.conx.logistics.mdm.dao.services.product.IWeightUnitDAOService;
import com.conx.logistics.mdm.dao.services.referencenumber.IReferenceNumberDAOService;
import com.conx.logistics.mdm.dao.services.referencenumber.IReferenceNumberTypeDAOService;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

@Transactional
public class MockApp extends Application {
	private static final long serialVersionUID = -5470222303880854277L;
	
	@PersistenceContext (unitName="pu")
    private EntityManager em;	
	
	@Autowired
	private UserTransaction userTransaction;
	
	@Autowired
	private EntityManagerFactory  conxLogisticsManagerFactory;
	
	@Autowired
	private IOrganizationDAOService orgDaoService;
	@Autowired
	private ICountryDAOService countryDaoService;
	@Autowired
	private ICountryStateDAOService countryStateDaoService;
	@Autowired
	private IUnlocoDAOService unlocoDaoService;
	@Autowired
	private IAddressDAOService addressDaoService;
	@Autowired
	private IPackUnitDAOService packUnitDaoService;
	@Autowired
	private IDimUnitDAOService dimUnitDaoService;
	@Autowired
	private IWeightUnitDAOService weightUnitDaoService;
	@Autowired
	private IProductTypeDAOService productTypeDaoService;
	@Autowired
	private IProductDAOService productDaoService;
	@Autowired
	private ICurrencyUnitDAOService currencyUnitDAOService;
	@Autowired
	private IASNDAOService asnDaoService;
	@Autowired
	private IASNPickupDAOService asnPickupDAOService;
	@Autowired
	private IASNDropOffDAOService asnDropOffDAOService;
	@Autowired
	private IContactDAOService contactDAOService;
	@Autowired
	private IDocTypeDAOService docTypeDOAService;
	@Autowired
	private IDockTypeDAOService dockTypeDOAService;
	@Autowired
	private IEntityMetadataDAOService entityMetadataDAOService;

	
	@Autowired
	private IReferenceNumberTypeDAOService referenceNumberTypeDaoService;
	@Autowired
	private IReferenceNumberDAOService referenceNumberDaoService;

	
	@Autowired
	private IWarehouseDAOService whseDAOService;	
	
	@Autowired
	private IReceiveDAOService rcvDaoService;
	@Autowired
	private IComponentDAOService componentDAOService;
	@Autowired
	private IEntityTypeDAOService entityTypeDAOService;
	@Autowired
	private IDataSourceDAOService dataSourceDAOService;
	

	@Override
	public void init() {
		setTheme("conx");
		
		//this.em = conxLogisticsManagerFactory.createEntityManager();
		
    	MasterDetailComponent md = null;
		try {
			//userTransaction.begin();
			TestDataManager.generateData(em, orgDaoService,countryDaoService, countryStateDaoService, unlocoDaoService, addressDaoService, packUnitDaoService, dimUnitDaoService, weightUnitDaoService, productTypeDaoService, productDaoService, currencyUnitDAOService, asnDaoService, asnPickupDAOService, asnDropOffDAOService, contactDAOService, docTypeDOAService, dockTypeDOAService, entityMetadataDAOService, referenceNumberTypeDaoService, referenceNumberDaoService, rcvDaoService, componentDAOService, entityTypeDAOService, dataSourceDAOService, whseDAOService);
			
			List<Receive> rvs = rcvDaoService.getAll();
			
			//md = UIComponentModelData.createReceiveSearchMasterDetail(componentDAOService, entityTypeDAOService, dataSourceDAOService, em);
			md = (MasterDetailComponent)componentDAOService.getByCode("searchReceives");
			LineEditorContainerComponent lep = md.getLineEditorPanel();
			LineEditorComponent le = lep.getLineEditors().iterator().next();

			EventBusManager ebm = new EventBusManager();
			PresenterFactory presenterFactory = new PresenterFactory(ebm, getLocale());
			IPresenter<?, ? extends EventBus> mainPresenter = presenterFactory.createPresenter(MultiLevelEntityEditorPresenter.class);
			MultiLevelEntityEditorEventBus mainEventBus = (MultiLevelEntityEditorEventBus) mainPresenter.getEventBus();
			mainEventBus.start(ebm, presenterFactory,md,em);
			
			Window w = new Window("Test Entity Editor App");
			w.setSizeFull();
			w.setLayout((Layout) mainPresenter.getView());
			setMainWindow(w);
			
			//userTransaction.commit();			
		} catch (Exception e) {
/*			try {
				userTransaction.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			e.printStackTrace();
		}
		
		
//		Window w = new Window();
//		w.setSizeFull();
//		
//		TaskDefinition td = new TaskDefinition();
//		td.setBpmn2ProcDefURL("");
//		td.setProcessId("whse.rcv.asn");
//		
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		def.setName("web.app.mock");
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//		TransactionStatus status = this.globalJtaTransactionManager.getTransaction(def);	
//		
//		try
//		{
//			pfs = defaultPageFlowEngine.startPageFlowSession("skeswa", td);
//			w.addComponent(pfs.getWizardComponent());
//			this.globalJtaTransactionManager.commit(status);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			this.globalJtaTransactionManager.rollback(status);
//		}
//		
//		this.setMainWindow(w);
	}
	
	public class SpringContextHelper {

	    private ApplicationContext context;

	    public SpringContextHelper(Application application) {
	        ServletContext servletContext = ((WebApplicationContext) application.getContext()).getHttpSession().getServletContext();
	        context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	    }

	    public Object getBean(final String beanRef) {
	        return context.getBean(beanRef);
	    }    
	}	
}
