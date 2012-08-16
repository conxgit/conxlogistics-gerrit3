package com.conx.logistics.data.uat.sprint2.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import com.conx.logistics.app.whse.rcv.rcv.domain.Receive;
import com.conx.logistics.kernel.datasource.dao.services.IDataSourceDAOService;
import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.mdm.domain.documentlibrary.FileEntry;
import com.conx.logistics.mdm.domain.note.NoteItem;

public class DataSourceData {
	
	public static DataSource RCV_BASIC_DS = null; 
	public static DataSource RCV_DEFAULT_DS = null;
	
	public static DataSource FE_DS = null;
	public static DataSource NI_DS = null; 

	
	public final static DataSource provideDefaultReceiveDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception
	{
		com.conx.logistics.kernel.metamodel.domain.EntityType rcvET = EntityTypeData.provide(entityTypeDAOService, em, Receive.class);
    	
		DataSource receiveDS = dataSourceDAOService.provide(rcvET);
		receiveDS.setCode("defaultReceiveDS");
		receiveDS.setName("DefaultReceiveDS");
		receiveDS = em.merge(receiveDS);
		
		String[] visibleFieldNames = {"id","code","name","dateCreated","dateLastUpdated","warehouse"};
		List<String> visibleFieldNamesSet = Arrays.asList(visibleFieldNames);	
		for ( DataSourceField fld : receiveDS.getDSFields())
		{
			if (visibleFieldNamesSet.contains(fld.getName()))
				fld.setHidden(false);
			else
				fld.setHidden(true);
			
			if ("warehouse".equals(fld.getName()))
			{
				fld.setValueXPath("name");
			}
		}	
		
		receiveDS = em.merge(receiveDS);
		em.flush();
	
		RCV_DEFAULT_DS = receiveDS;
		
		return receiveDS;
	}
	
	public final static DataSource provideBasicFormReceiveDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception
	{
		//--- Basic
		//-Code/Name
		//-Warehouse
		com.conx.logistics.kernel.metamodel.domain.EntityType rcvET = EntityTypeData.provide(entityTypeDAOService, em, Receive.class);
		DataSource receiveDS = new DataSource("receiveBasicAttrDS", rcvET);
		receiveDS = dataSourceDAOService.add(receiveDS);
	
		DataSourceField id = dataSourceDAOService.getFieldByName(RCV_DEFAULT_DS, "id");
		DataSourceField code = dataSourceDAOService.getFieldByName(RCV_DEFAULT_DS, "code");
		DataSourceField name = dataSourceDAOService.getFieldByName(RCV_DEFAULT_DS, "name");
		DataSourceField dateCreated = dataSourceDAOService.getFieldByName(RCV_DEFAULT_DS, "dateCreated");		
		DataSourceField dateLastUpdated = dataSourceDAOService.getFieldByName(RCV_DEFAULT_DS, "dateLastUpdated");
		
		DataSourceField warehouse = dataSourceDAOService.getFieldByName(RCV_DEFAULT_DS, "warehouse");
		warehouse.setValueXPath("code");

		
		DataSourceField fields[] = { id, code, name, dateCreated, dateLastUpdated, warehouse};
	    Set<DataSourceField> fieldSet = new HashSet<DataSourceField>(Arrays.asList(fields));
		
		receiveDS = dataSourceDAOService.addFields(receiveDS, fieldSet);
		
		receiveDS = em.merge(receiveDS);
		em.flush();
		
		RCV_BASIC_DS = receiveDS;
		
		return receiveDS;
	}	
	
	public final static DataSource provideFileEntryDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception
	{
		com.conx.logistics.kernel.metamodel.domain.EntityType feET = EntityTypeData.provide(entityTypeDAOService, em, FileEntry.class);
    	
		DataSource feDS = dataSourceDAOService.provide(feET);
		feDS.setCode("fileEntryDS");
		feDS.setName("fileEntryDS");
		feDS = em.merge(feDS);
		
		String[] visibleFieldNames = {"title","size","createDate","modifiedDate","dateCreated","docType","mimeType"};
		List<String> visibleFieldNamesSet = Arrays.asList(visibleFieldNames);	
		for ( DataSourceField fld : feDS.getDSFields())
		{
			if (visibleFieldNamesSet.contains(fld.getName()))
				fld.setHidden(false);
			else
				fld.setHidden(true);
			
			if ("docType".equals(fld.getName()))
			{
				fld.setValueXPath("code");
			}
		}	
		
		feDS = em.merge(feDS);
		em.flush();
	
		FE_DS = feDS;
		
		return feDS;
	}	
	
	
	public final static DataSource provideNoteItemDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception
	{
		com.conx.logistics.kernel.metamodel.domain.EntityType niET = EntityTypeData.provide(entityTypeDAOService, em, NoteItem.class);
    	
		DataSource niDS = dataSourceDAOService.provide(niET);
		niDS.setCode("noteDS");
		niDS.setName("noteDS");
		niDS = em.merge(niDS);
		
		String[] visibleFieldNames = {"id","code","name","dateCreated","dateLastUpdated","content"};
		List<String> visibleFieldNamesSet = Arrays.asList(visibleFieldNames);	
		for ( DataSourceField fld : niDS.getDSFields())
		{
			if (visibleFieldNamesSet.contains(fld.getName()))
				fld.setHidden(false);
			else
				fld.setHidden(true);
		}	
		
		niDS = em.merge(niDS);
		em.flush();
	
		NI_DS = niDS;
		
		return niDS;
	}		
}
