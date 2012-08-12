package com.conx.logistics.data.uat.sprint2.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
import com.conx.logistics.kernel.ui.components.domain.form.ConXCollapseableSectionForm;
import com.conx.logistics.kernel.ui.components.domain.form.FieldSet;
import com.conx.logistics.kernel.ui.components.domain.form.FieldSetField;
import com.conx.logistics.kernel.ui.components.domain.layout.ConXGridLayout;
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
		rcvBaicFormLE = (LineEditorComponent) componentDAOService.add((AbstractConXComponent)rcvBaicFormLE);
		lecc.getLineEditors().add(rcvBaicFormLE);
		rcvSearchMLEE.setLineEditorPanel(lecc);
		
		//--  DS
		DataSource basicRcvDS = getBasicFormRCVDS(entityTypeDAOService,dataSourceDAOService,em);
		
		//--Layout
		ConXGridLayout gl = new ConXGridLayout();
		gl = (ConXGridLayout) componentDAOService.add((AbstractConXComponent)gl);
		
		//-- FieldSet
		FieldSet fs1 = new FieldSet("Basic",1,gl);
		fs1 = (FieldSet) componentDAOService.add((AbstractConXComponent)fs1);
		Set<FieldSetField> fsfs = new HashSet<FieldSetField>();
		FieldSetField fsf1;
		for (DataSourceField dsf : basicRcvDS.getDSFields())
		{
			fsf1 = new FieldSetField(fs1,dsf);
			fsfs.add(fsf1);
		}
		fs1.getFields().addAll(fsfs);
		fs1 = (FieldSet) componentDAOService.update((AbstractConXComponent)fs1);
		List<FieldSet> fss = Arrays.asList(fs1);

		//-- Form
		ConXCollapseableSectionForm cform = new ConXCollapseableSectionForm(basicRcvDS,gl,fss);
		cform = (ConXCollapseableSectionForm) componentDAOService.add((AbstractConXComponent)cform);
		rcvBaicFormLE.setContent(cform);
		rcvBaicFormLE = (LineEditorComponent) componentDAOService.update((AbstractConXComponent)rcvBaicFormLE);
		
		
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
	
	private static DataSource getBasicFormRCVDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws ClassNotFoundException {
		if (DataSourceData.RCV_BASIC_DS == null)
		{
			DataSourceData.provideBasicFormReceiveDS(entityTypeDAOService, dataSourceDAOService, em);
		}
		
		return DataSourceData.RCV_BASIC_DS;
	}	
}
