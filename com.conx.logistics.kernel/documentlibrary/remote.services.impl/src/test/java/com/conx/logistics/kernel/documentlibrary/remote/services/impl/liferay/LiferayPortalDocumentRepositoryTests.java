package com.conx.logistics.kernel.documentlibrary.remote.services.impl.liferay;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

import org.apache.http.entity.mime.content.InputStreamBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.conx.logistics.kernel.documentlibrary.remote.services.impl.LiferayPortalDocumentRepositoryImpl;
import com.conx.logistics.kernel.metamodel.dao.services.impl.EntityTypeDAOImpl;
import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;
import com.conx.logistics.mdm.domain.documentlibrary.Folder;

@ContextConfiguration(locations = { "/META-INF/tm.jta-module-context.xml",
        "/META-INF/persistence.datasource-module-context.xml",
        "/META-INF/persistence.dynaconfiguration-module-context.xml",
        "/META-INF/mdm.dao.services.impl-module-context.xml",
        "/META-INF/metamodel.dao.jpa.persistence-module-context.xml",
        "/META-INF/module-context.xml"
        })
public class LiferayPortalDocumentRepositoryTests extends AbstractTestNGSpringContextTests {
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private EntityManagerFactory  conxLogisticsManagerFactory;
	
	private UserTransaction userTransactionManager;

	private EntityManager em;
	
	
	@Autowired
	private EntityTypeDAOImpl entityTypeDAOService;
	
	@Autowired
	private LiferayPortalDocumentRepositoryImpl docRepoRemoteService;
	
	@BeforeClass
	public void setUp() throws Exception {
        Assert.assertNotNull(applicationContext);
        Assert.assertNotNull(conxLogisticsManagerFactory);
        
    	em = conxLogisticsManagerFactory.createEntityManager();
        
        userTransactionManager = (UserTransaction) applicationContext.getBean("globalBitronixTransactionManager");
        Assert.assertNotNull(userTransactionManager);
        
        Assert.assertNotNull(entityTypeDAOService);
        Assert.assertNotNull(docRepoRemoteService);
        
        
        docRepoRemoteService.init();
    }	
	
	@AfterClass
	public void tearDown() throws Exception {
		em.close();
	}
	
	
	
    @Test
    public void testEnsureFolder() throws Exception {
    	Folder fldr = null;
    	boolean isAvailable = docRepoRemoteService.isAvailable();
    	Assert.assertTrue(isAvailable); 
    	
    	boolean res = docRepoRemoteService.folderExists(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
    	if (!res)
    	{
	    	fldr = docRepoRemoteService.addFolder(docRepoRemoteService.getConxlogiFolderId(),"Receive123", "Receive123");
	    	Assert.assertNotNull(fldr);
	    	
	    	fldr = docRepoRemoteService.getFolderByName(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	Assert.assertNotNull(fldr);
	    	
	    	res = docRepoRemoteService.folderExists(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	Assert.assertTrue(res);    	
    	}
    }
    
    
    @Test(dependsOnMethods={"testEnsureFolder"})
    public void testAddAndDeleteFile() throws Exception {
    	Folder fldr = null;
    	boolean isAvailable = docRepoRemoteService.isAvailable();
    	Assert.assertTrue(isAvailable); 
    	
    	fldr = docRepoRemoteService.getFolderByName(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
    	Assert.assertNotNull(fldr);    	
    	
    	boolean res = docRepoRemoteService.fileEntryExists(Long.toString(fldr.getFolderId()),"BoL");
    	if (!res)
    	{
    		InputStream is = new ByteArrayInputStream("test string".getBytes());
    		FileEntry fe = docRepoRemoteService.addFileEntry(Long.toString(fldr.getFolderId()), "C:/dev/gitrepos/conxlogistics-gerrit3/com.conx.logistics.kernel/com.conx.logistics.data/uat.sprint2.data/src/main/resources/bol.pdf", "application/pdf", "BoL", "BoL");
/*	    	fldr = docRepoRemoteService.addFolder(docRepoRemoteService.getConxlogiFolderId(),"Receive123", "Receive123");
	    	Assert.assertNotNull(fldr);
	    	
	    	fldr = docRepoRemoteService.getFolderByName(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	Assert.assertNotNull(fldr);
	    	
	    	res = docRepoRemoteService.folderExists(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	Assert.assertTrue(res);    	
	    	
	    	docRepoRemoteService.deleteFolderById(Long.toString(fldr.getFolderId()));
	    	
	    	res = docRepoRemoteService.folderExists(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	Assert.assertFalse(res);*/
    	}
    	else
    	{
/*	    	docRepoRemoteService.deleteFolderByName(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	
	    	res = docRepoRemoteService.folderExists(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	Assert.assertFalse(res);  */  		
    	}
    }   
    
    @Test(dependsOnMethods={"testAddAndDeleteFile"},enabled=false)
    public void testDeleteFolder() throws Exception {
    	Folder fldr = null;
    	boolean isAvailable = docRepoRemoteService.isAvailable();
    	Assert.assertTrue(isAvailable); 
    	
    	boolean res = docRepoRemoteService.folderExists(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
    	if (res)
    	{
	    	docRepoRemoteService.deleteFolderByName(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	
	    	res = docRepoRemoteService.folderExists(docRepoRemoteService.getConxlogiFolderId(),"Receive123");
	    	Assert.assertFalse(res);    		
    	}
    }    
}
