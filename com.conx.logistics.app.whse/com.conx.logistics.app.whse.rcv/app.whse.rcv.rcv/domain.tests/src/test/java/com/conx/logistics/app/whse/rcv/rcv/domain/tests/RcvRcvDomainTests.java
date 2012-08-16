package com.conx.logistics.app.whse.rcv.rcv.domain.tests;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.conx.logistics.app.whse.rcv.rcv.dao.services.impl.ReceiveDAOImpl;
import com.conx.logistics.app.whse.rcv.rcv.domain.Receive;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;

@ContextConfiguration(locations = { "/META-INF/tm.jta-module-context.xml",
        "/META-INF/persistence.datasource-module-context.xml",
        "/META-INF/jbpm.persistence.datasource-module-context.xml",
        "/META-INF/persistence.dynaconfiguration-module-context.xml",
        "/META-INF/mdm.dao.services.impl-module-context.xml",
        "/META-INF/metamodel.dao.jpa.persistence-module-context.xml",
        "/META-INF/datasource.dao.jpa.persistence-module-context.xml",
        "/META-INF/documentlibrary.remote.services.impl-module-context.xml",
        "/META-INF/rcv.rcv.dao.jpa.persistence-module-context.xml"
        })
public class RcvRcvDomainTests extends AbstractTestNGSpringContextTests {
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private EntityManagerFactory  conxLogisticsManagerFactory;
	
	private UserTransaction userTransactionManager;

	private EntityManager em;
	
	
	@Autowired
	private IEntityTypeDAOService entityTypeDAOService;
	
	@Autowired
	private ReceiveDAOImpl rcvDAOService;
	
	@BeforeClass
	public void setUp() throws Exception {
        Assert.assertNotNull(applicationContext);
        Assert.assertNotNull(conxLogisticsManagerFactory);
        
    	em = conxLogisticsManagerFactory.createEntityManager();
        
        userTransactionManager = (UserTransaction) applicationContext.getBean("globalBitronixTransactionManager");
        Assert.assertNotNull(userTransactionManager);
        
        Assert.assertNotNull(entityTypeDAOService);
    }	
	
	@AfterClass
	public void tearDown() throws Exception {
		em.close();
	}
	
    @Test
    public void testRcvCreation() throws Exception {
    	Receive rcv = new Receive();
    	rcv = rcvDAOService.add(rcv);
    	
    	Assert.assertTrue(rcv.getId() > 0);
    }
}
