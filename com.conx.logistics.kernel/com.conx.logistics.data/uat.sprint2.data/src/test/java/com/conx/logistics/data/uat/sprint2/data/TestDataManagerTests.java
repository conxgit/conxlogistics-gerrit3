package com.conx.logistics.data.uat.sprint2.data;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.conx.logistics.app.whse.dao.services.IDockTypeDAOService;
import com.conx.logistics.app.whse.dao.services.IWarehouseDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNDropOffDAOService;
import com.conx.logistics.app.whse.rcv.asn.dao.services.IASNPickupDAOService;
import com.conx.logistics.app.whse.rcv.asn.domain.ASN;
import com.conx.logistics.app.whse.rcv.rcv.dao.services.IReceiveDAOService;
import com.conx.logistics.app.whse.rcv.rcv.domain.Receive;
import com.conx.logistics.kernel.datasource.dao.services.IDataSourceDAOService;
import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.documentlibrary.remote.services.IRemoteDocumentRepository;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.metamodel.domain.BasicAttribute;
import com.conx.logistics.kernel.metamodel.domain.PluralAttribute;
import com.conx.logistics.kernel.metamodel.domain.SingularAttribute;
import com.conx.logistics.kernel.ui.components.dao.services.IComponentDAOService;
import com.conx.logistics.mdm.dao.services.IAddressDAOService;
import com.conx.logistics.mdm.dao.services.IContactDAOService;
import com.conx.logistics.mdm.dao.services.ICountryDAOService;
import com.conx.logistics.mdm.dao.services.ICountryStateDAOService;
import com.conx.logistics.mdm.dao.services.IEntityMetadataDAOService;
import com.conx.logistics.mdm.dao.services.IOrganizationDAOService;
import com.conx.logistics.mdm.dao.services.IUnlocoDAOService;
import com.conx.logistics.mdm.dao.services.currency.ICurrencyUnitDAOService;
import com.conx.logistics.mdm.dao.services.documentlibrary.IDocTypeDAOService;
import com.conx.logistics.mdm.dao.services.documentlibrary.IFolderDAOService;
import com.conx.logistics.mdm.dao.services.note.INoteDAOService;
import com.conx.logistics.mdm.dao.services.product.IDimUnitDAOService;
import com.conx.logistics.mdm.dao.services.product.IPackUnitDAOService;
import com.conx.logistics.mdm.dao.services.product.IProductDAOService;
import com.conx.logistics.mdm.dao.services.product.IProductTypeDAOService;
import com.conx.logistics.mdm.dao.services.product.IWeightUnitDAOService;
import com.conx.logistics.mdm.dao.services.referencenumber.IReferenceNumberDAOService;
import com.conx.logistics.mdm.dao.services.referencenumber.IReferenceNumberTypeDAOService;

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
        "/META-INF/spring/data.uat.sprint2.data-module-context.xml"
        
        })
public class TestDataManagerTests extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;
    
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private EntityManagerFactory  conxLogisticsManagerFactory;
	
	private UserTransaction userTransactionManager;

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
	
	@Autowired
	private IWarehouseDAOService whseDAOService;
	
	
	@Autowired
	private IRemoteDocumentRepository documentRepositoryService;
	
	@Autowired
	private IFolderDAOService folderDAOService;
	
	@Autowired
	private INoteDAOService noteDAOService;	

	private ASN asn = null;	
	
	@BeforeClass
	public void setUp() throws Exception {
        Assert.assertNotNull(applicationContext);
        Assert.assertNotNull(conxLogisticsManagerFactory);
        
        
        Assert.assertNotNull(documentRepositoryService);
        Assert.assertNotNull(folderDAOService);
        /*
        em = conxLogisticsManagerFactory.createEntityManager();
        
        userTransactionManager = (UserTransaction) applicationContext.getBean("globalBitronixTransactionManager");
        Assert.assertNotNull(userTransactionManager);
        
        Assert.assertNotNull(rcvDaoService);
        Assert.assertNotNull(componentDAOService);
        Assert.assertNotNull(entityTypeDAOService);
        Assert.assertNotNull(dataSourceDAOService);*/
    }	
	
	@AfterClass
	public void tearDown() throws Exception {
	}
	
/*    @Test
    public void testEntitTypeCreation() throws Exception {
    	Metamodel mm = em.getMetamodel();
    	Assert.assertNotNull(mm);
    	
    	EntityType<Receive> me = mm.entity(Receive.class);
    	
    	com.conx.logistics.kernel.metamodel.domain.EntityType et = entityTypeDAOService.provide(me);
    	Assert.assertNotNull(et);
    	
        List<BasicAttribute> battrs = entityTypeDAOService.getAllBasicAttributesByEntityType(et);
        Assert.assertTrue(battrs.size() > 0);
        
        List<SingularAttribute> sattrs = entityTypeDAOService.getAllSingularAttributesByEntityType(et);
        Assert.assertTrue(sattrs.size() > 0);     
        
        List<PluralAttribute> pattrs = entityTypeDAOService.getAllPluralAttributesByEntityType(et);
        Assert.assertTrue(pattrs.size() > 0);          
    }	*/

    @Test
    public void testDataManagerSprint1Data() throws Exception {
		try
		{
			asn  = TestDataManager.createSprint1Data(null, em, orgDaoService, countryDaoService, countryStateDaoService, unlocoDaoService, addressDaoService, packUnitDaoService, dimUnitDaoService, weightUnitDaoService, productTypeDaoService, productDaoService, currencyUnitDAOService, asnDaoService, asnPickupDAOService, asnDropOffDAOService, contactDAOService, docTypeDOAService, dockTypeDOAService, entityMetadataDAOService, referenceNumberTypeDaoService, referenceNumberDaoService, rcvDaoService, componentDAOService, entityTypeDAOService, dataSourceDAOService, whseDAOService);
			Assert.assertEquals(asn.getAsnLines().size(), 2);
			Assert.assertEquals(asn.getRefNumbers().size(), 2);
			
/*			TestDataManager.generateData(em, orgDaoService, countryDaoService, countryStateDaoService, unlocoDaoService, addressDaoService, packUnitDaoService, dimUnitDaoService, weightUnitDaoService, productTypeDaoService, productDaoService, currencyUnitDAOService, asnDaoService, asnPickupDAOService, asnDropOffDAOService, contactDAOService, docTypeDOAService, dockTypeDOAService, entityMetadataDAOService, referenceNumberTypeDaoService, referenceNumberDaoService, rcvDaoService, componentDAOService, entityTypeDAOService, dataSourceDAOService, whseDAOService,documentRepositoryService,folderDAOService);
			
	    	DataSource receiveDS = dataSourceDAOService.getByCode("defaultReceiveDS");
	    	Assert.assertNotNull(receiveDS);
	    	Assert.assertEquals(receiveDS.getDSFields().size(),56);
	    	
	    	DataSourceField fld = receiveDS.getField("warehouse");
	    	Assert.assertNotNull(fld);
	    
	    	Set<String> vfn = receiveDS.getVisibleFieldNames();
	    	Assert.assertNotNull(vfn);
	    	Assert.assertEquals(vfn.size(), 6);
	    	
	    	
	    	Assert.assertTrue(fld.isNestedAttribute());
	    	Assert.assertTrue("warehouse.code".equals(fld.getJPAPath()));*/			
		}
		catch (Exception e) 
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
			throw e;
		}		
/*    	TestDataManager.generateData(em, countryDaoService, countryStateDaoService, unlocoDaoService, addressDaoService, packUnitDaoService, dimUnitDaoService, weightUnitDaoService, productTypeDaoService, productDaoService, currencyUnitDAOService, asnDaoService, asnPickupDAOService, asnDropOffDAOService, contactDAOService, docTypeDOAService, dockTypeDOAService, entityMetadataDAOService, referenceNumberTypeDaoService, referenceNumberDaoService, rcvDaoService, componentDAOService, entityTypeDAOService, dataSourceDAOService, whseDAOService);
    	
    	com.conx.logistics.kernel.metamodel.domain.EntityType rcvET = EntityTypeData.provide(entityTypeDAOService, em, Receive.class);
    	Assert.assertNotNull(rcvET);
    	
    	DataSource receiveDS = dataSourceDAOService.getByCode("defaultReceiveDS");
    	Assert.assertNotNull(receiveDS);
    	Assert.assertEquals(receiveDS.getDSFields().size(),56);
    	
    	DataSourceField fld = receiveDS.getField("warehouse");
    	Assert.assertNotNull(fld);
    
    	Set<String> vfn = receiveDS.getVisibleFieldNames();
    	Assert.assertNotNull(vfn);
    	Assert.assertEquals(vfn.size(), 6);
    	
    	
    	Assert.assertTrue(fld.isNestedAttribute());
    	Assert.assertTrue("warehouse.name".equals(fld.getJPAPath()));*/
    }    
    
    @Test(dependsOnMethods={"testDataManagerSprint1Data"})
    public void testDataManagerSprint2Data() throws Exception {
		try
		{
			Assert.assertNotNull(asn);
			Receive rcv = TestDataManager.createPrint2Data(asn, null, em, orgDaoService, countryDaoService, countryStateDaoService, unlocoDaoService, addressDaoService, packUnitDaoService, dimUnitDaoService, weightUnitDaoService, productTypeDaoService, productDaoService, currencyUnitDAOService, asnDaoService, asnPickupDAOService, asnDropOffDAOService, contactDAOService, docTypeDOAService, dockTypeDOAService, entityMetadataDAOService, referenceNumberTypeDaoService, referenceNumberDaoService, rcvDaoService, componentDAOService, entityTypeDAOService, dataSourceDAOService, whseDAOService,documentRepositoryService,folderDAOService,noteDAOService);
			Assert.assertNotNull(rcv);
		}
		catch (Exception e) 
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
			throw e;
		}		
    }     
}
