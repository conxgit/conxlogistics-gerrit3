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

public class DataSourceData {
	
	public static DataSource RCV_BASIC_DS = null; 
	public static DataSource RCV_DEFAULT_DS = null;

	
	public final static DataSource provideDefaultReceiveDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws ClassNotFoundException
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
				fld = em.merge(fld);
			}
		}	
		
		receiveDS = em.merge(receiveDS);
		em.flush();
	
		RCV_DEFAULT_DS = receiveDS;
		
		return receiveDS;
	}
	
	public final static DataSource provideBasicFormReceiveDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws ClassNotFoundException
	{
		//--- Basic
		//-Code/Name
		//-Warehouse
		com.conx.logistics.kernel.metamodel.domain.EntityType rcvET = EntityTypeData.provide(entityTypeDAOService, em, Receive.class);
		DataSource receiveDS = new DataSource("receiveBasicAttrDS", rcvET);
		receiveDS = dataSourceDAOService.add(receiveDS);
	
		DataSourceField id = dataSourceDAOService.getFieldByName(receiveDS, "id");
		DataSourceField code = dataSourceDAOService.getFieldByName(receiveDS, "code");
		DataSourceField name = dataSourceDAOService.getFieldByName(receiveDS, "name");
		DataSourceField dateCreated = dataSourceDAOService.getFieldByName(receiveDS, "dateCreated");		
		DataSourceField dateLastUpdated = dataSourceDAOService.getFieldByName(receiveDS, "dateLastUpdated");
		
		DataSourceField warehouse = dataSourceDAOService.getFieldByName(receiveDS, "warehouse");
		warehouse.setValueXPath("code");

		
		DataSourceField fields[] = { id, code, name, dateCreated, dateLastUpdated, warehouse};
	    Set<DataSourceField> fieldSet = new HashSet<DataSourceField>(Arrays.asList(fields));
		
		receiveDS = dataSourceDAOService.addFields(receiveDS, fieldSet);
		
		receiveDS = em.merge(receiveDS);
		em.flush();
		
		RCV_BASIC_DS = receiveDS;
		
		return receiveDS;
	}	
}
