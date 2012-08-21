package com.conx.logistics.kernel.ui.editors.builder.tests;


import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.PresenterFactory;

import com.conx.logistics.app.whse.dao.services.IDockTypeDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNDropOffDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNPickupDAOService;
import com.conx.logistics.app.whse.rcv.rcv.dao.services.IReceiveDAOService;
import com.conx.logistics.data.uat.sprint2.data.UIComponentModelData;
import com.conx.logistics.kernel.datasource.dao.services.IDataSourceDAOService;
import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.metamodel.domain.AbstractAttribute;
import com.conx.logistics.kernel.metamodel.domain.BasicAttribute;
import com.conx.logistics.kernel.metamodel.domain.PluralAttribute;
import com.conx.logistics.kernel.metamodel.domain.SingularAttribute;
import com.conx.logistics.kernel.ui.components.dao.services.IComponentDAOService;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
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
import com.conx.logistics.mdm.domain.product.Product;

@ContextConfiguration(locations = { "/META-INF/tm.jta-module-context.xml",
        "/META-INF/persistence.datasource-module-context.xml",
        "/META-INF/persistence.dynaconfiguration-module-context.xml",
        "/META-INF/datasource.dao.jpa.persistence-module-context.xml",
        "/META-INF/mdm.dao.services.impl-module-context.xml",
        "/META-INF/metamodel.dao.jpa.persistence-module-context.xml",
        "/META-INF/datasource.dao.jpa.persistence-module-context.xml",
        "/META-INF/components.dao.jpa.persistence-module-context.xml",
        "/META-INF/documentlibrary.remote.services.impl-module-context.xml",
        "/META-INF/app.whse.dao.jpa.persistence-module-context.xml",
        "/META-INF/app.whse.rcv.asn.dao.jpa.persistence-module-context.xml",
        "/META-INF/app.whse.rcv.rcv.dao.jpa.persistence-module-context.xml",
        
        })
public class EditorfactoryTests extends AbstractTestNGSpringContextTests {
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private EntityManagerFactory  conxLogisticsManagerFactory;
	
	private UserTransaction userTransactionManager;

	private EntityManager em;
	
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
	private IReceiveDAOService rcvDaoService;
	@Autowired
	private IComponentDAOService componentDAOService;
	@Autowired
	private IEntityTypeDAOService entityTypeDAOService;
	@Autowired
	private IDataSourceDAOService dataSourceDAOService;	
	
	@BeforeClass
	public void setUp() throws Exception {
        Assert.assertNotNull(applicationContext);
        Assert.assertNotNull(conxLogisticsManagerFactory);
        
    	em = conxLogisticsManagerFactory.createEntityManager();
        
        userTransactionManager = (UserTransaction) applicationContext.getBean("globalBitronixTransactionManager");
        Assert.assertNotNull(userTransactionManager);
        
        Assert.assertNotNull(rcvDaoService);
        Assert.assertNotNull(componentDAOService);
        Assert.assertNotNull(entityTypeDAOService);
        Assert.assertNotNull(dataSourceDAOService);
    }	
	
	@AfterClass
	public void tearDown() throws Exception {
		em.close();
	}
	
/*    @Test
    public void testCreateUIComponents() throws Exception {
    	MasterDetailComponent md = UIComponentModelData.createReceiveSearchMasterDetail(componentDAOService, entityTypeDAOService, dataSourceDAOService, em);
    	Assert.assertNotNull(md);
    	
		EventBusManager ebm = new EventBusManager();
		PresenterFactory presenterFactory = new PresenterFactory(ebm, Locale.getDefault());
		IPresenter<?, ? extends EventBus> mainPresenter = presenterFactory.createPresenter(MultiLevelEntityEditorPresenter.class);
		MultiLevelEntityEditorEventBus mainEventBus = (MultiLevelEntityEditorEventBus) mainPresenter.getEventBus();
		//mainEventBus.start(ebm, presenterFactory,md,em);    	
    }*/
}
