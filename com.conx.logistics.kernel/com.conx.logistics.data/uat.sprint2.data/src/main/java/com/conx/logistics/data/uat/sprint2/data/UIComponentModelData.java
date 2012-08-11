package com.conx.logistics.data.uat.sprint2.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import com.conx.logistics.app.whse.rcv.rcv.domain.Receive;
import com.conx.logistics.kernel.datasource.dao.services.IDataSourceDAOService;
import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.ui.components.dao.services.IComponentDAOService;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorContainerComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.components.domain.table.ConXTable;

public class UIComponentModelData {
	public final static MasterDetailComponent createReceiveSearchMasterDetail(IComponentDAOService componentDAOService, IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws ClassNotFoundException
	{
		//-- ML E.E.
		DataSource rcvDefaultDS = getDefaultRCVDS(entityTypeDAOService,dataSourceDAOService,em);	
		MasterDetailComponent rcvSearchMLEE = new MasterDetailComponent("searchReceives","Receives",rcvDefaultDS);
		rcvSearchMLEE = (MasterDetailComponent) componentDAOService.add((AbstractConXComponent)rcvSearchMLEE);

		
		//-- RCV Search Table
		ConXTable rcvTable = new ConXTable();
		rcvTable = (ConXTable) componentDAOService.add((AbstractConXComponent)rcvTable);
		rcvSearchMLEE.setTable(rcvTable);
		
		LineEditorContainerComponent lecc = new LineEditorContainerComponent(rcvSearchMLEE.getCode()+"-lineEditorContainerComponent",rcvSearchMLEE.getName()+" Line Editor");
		try {
			lecc = (LineEditorContainerComponent) componentDAOService.add((AbstractConXComponent)lecc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//-- RCV Basic Line Editor
		LineEditorComponent rcvBaicFormLE = new LineEditorComponent(lecc.getCode()+"-basicAttrs","Basic",lecc);
		rcvBaicFormLE = em.merge(rcvBaicFormLE);
		lecc.getLineEditors().add(rcvBaicFormLE);
		rcvSearchMLEE.setLineEditorPanel(lecc);
		
		rcvSearchMLEE = (MasterDetailComponent) componentDAOService.update((AbstractConXComponent)rcvSearchMLEE);
		
		return rcvSearchMLEE;
	}

	private static DataSource getDefaultRCVDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws ClassNotFoundException {
		if (DataSourceData.RCV_DEFAULT_DS == null)
		{
			DataSourceData.provideDefaultReceiveDS(entityTypeDAOService, dataSourceDAOService, em);
		}
		
		return DataSourceData.RCV_DEFAULT_DS;
	}
}
