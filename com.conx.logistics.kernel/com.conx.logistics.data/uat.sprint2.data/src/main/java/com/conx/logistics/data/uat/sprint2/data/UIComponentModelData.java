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
import com.conx.logistics.kernel.ui.components.domain.attachment.AttachmentEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.form.ConXCollapseableSectionForm;
import com.conx.logistics.kernel.ui.components.domain.form.ConXSimpleForm;
import com.conx.logistics.kernel.ui.components.domain.form.FieldSet;
import com.conx.logistics.kernel.ui.components.domain.form.FieldSetField;
import com.conx.logistics.kernel.ui.components.domain.form.FormField;
import com.conx.logistics.kernel.ui.components.domain.layout.ConXGridLayout;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorContainerComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.components.domain.note.NoteEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.table.ConXTable;

public class UIComponentModelData {
	public final static MasterDetailComponent createReceiveSearchMasterDetail(IComponentDAOService componentDAOService, IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception
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
		
		/**
		 * 
		 * 
		 * Line Editors
		 * 
		 * 
		 */
		
		//A. -- RCV Basic Line Editor
		LineEditorComponent rcvBaicFormLE = new LineEditorComponent(lecc.getCode()+"-basicAttrs","Basic",lecc);
		rcvBaicFormLE = (LineEditorComponent) componentDAOService.add((AbstractConXComponent)rcvBaicFormLE);
		lecc.getLineEditors().add(rcvBaicFormLE);
		rcvSearchMLEE.setLineEditorPanel(lecc);
		
		//--  DS
		DataSource basicRcvDS = getBasicFormRCVDS(entityTypeDAOService,dataSourceDAOService,em);
		
		//--Layout
		ConXGridLayout gl = new ConXGridLayout();
		gl = (ConXGridLayout) componentDAOService.add((AbstractConXComponent)gl);
		
		//-- FieldSet: Collapseable form
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
		
		//-- FormField's: Simple Form
		Set<FormField> ffs = new HashSet<FormField>();
		FormField ff;
		for (DataSourceField dsf : basicRcvDS.getDSFields())
		{
			ff = new FormField(dsf);
			ffs.add(ff);
		}		
		
		//-- Simple Form
		ConXSimpleForm sform = new ConXSimpleForm(basicRcvDS,ffs);
		sform = (ConXSimpleForm) componentDAOService.add((AbstractConXComponent)sform);

		//-- Collapseable Form
		ConXCollapseableSectionForm cform = new ConXCollapseableSectionForm(basicRcvDS,gl,fss);
		cform = (ConXCollapseableSectionForm) componentDAOService.add((AbstractConXComponent)cform);
		
		
		//-- Update Line Editor
		rcvBaicFormLE.setContent(sform);
		rcvBaicFormLE = (LineEditorComponent) componentDAOService.update((AbstractConXComponent)rcvBaicFormLE);
		
		
		//A. -- Attachments
		LineEditorComponent attachmentsLE = provideAttachmentLineEditor(
				componentDAOService, entityTypeDAOService,
				dataSourceDAOService, em, lecc);
		
		//B. -- Notes
		LineEditorComponent notesLE = provideNotesLineEditor(
				componentDAOService, entityTypeDAOService,
				dataSourceDAOService, em, lecc);		

		//-- Update EE
		lecc.getLineEditors().add(attachmentsLE);
		lecc.getLineEditors().add(notesLE);
		rcvSearchMLEE = (MasterDetailComponent) componentDAOService.update((AbstractConXComponent)rcvSearchMLEE);
		
		return rcvSearchMLEE;
	}

	private static LineEditorComponent provideNotesLineEditor(
			IComponentDAOService componentDAOService,
			IEntityTypeDAOService entityTypeDAOService,
			IDataSourceDAOService dataSourceDAOService, EntityManager em,
			LineEditorContainerComponent lecc) throws Exception {
		LineEditorComponent notesLE = new LineEditorComponent(lecc.getCode()+"-notes","Notes",lecc);
		notesLE = (LineEditorComponent) componentDAOService.add((AbstractConXComponent)notesLE);
		lecc.getLineEditors().add(notesLE);

		
		//--  NoteItem DS
		DataSource noteItemDS = getNoteItemDS(entityTypeDAOService,dataSourceDAOService,em);
		
		//-- Notes Table
		NoteEditorComponent nec = new NoteEditorComponent(noteItemDS);
		nec = (NoteEditorComponent) componentDAOService.update((AbstractConXComponent)nec);
		notesLE.setContent(nec);
		notesLE.setDataSource(noteItemDS);
		notesLE = (LineEditorComponent) componentDAOService.update((AbstractConXComponent)notesLE);
		return notesLE;
	}

	private static LineEditorComponent provideAttachmentLineEditor(
			IComponentDAOService componentDAOService,
			IEntityTypeDAOService entityTypeDAOService,
			IDataSourceDAOService dataSourceDAOService, EntityManager em,
			LineEditorContainerComponent lecc) throws Exception {
		LineEditorComponent attachmentsLE = new LineEditorComponent(lecc.getCode()+"-attachments","Attachments",lecc);
		attachmentsLE = (LineEditorComponent) componentDAOService.add((AbstractConXComponent)attachmentsLE);

		
		//--  FileEntry DS
		DataSource fileEntryDS = getFileEntryDS(entityTypeDAOService,dataSourceDAOService,em);
		
		//-- Attachement Table
		AttachmentEditorComponent aec = new AttachmentEditorComponent(fileEntryDS);
		aec = (AttachmentEditorComponent) componentDAOService.update((AbstractConXComponent)aec);
		attachmentsLE.setContent(aec);
		attachmentsLE.setDataSource(fileEntryDS);
		attachmentsLE = (LineEditorComponent) componentDAOService.update((AbstractConXComponent)attachmentsLE);
		return attachmentsLE;
	}

	private static DataSource getDefaultRCVDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception {
		if (DataSourceData.RCV_DEFAULT_DS == null)
		{
			DataSourceData.provideDefaultReceiveDS(entityTypeDAOService, dataSourceDAOService, em);
		}
		
		return DataSourceData.RCV_DEFAULT_DS;
	}
	
	private static DataSource getBasicFormRCVDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception {
		if (DataSourceData.RCV_BASIC_DS == null)
		{
			DataSourceData.provideBasicFormReceiveDS(entityTypeDAOService, dataSourceDAOService, em);
		}
		
		return DataSourceData.RCV_BASIC_DS;
	}	
	
	/**
	 * 
	 * 
	 * Generic DS's
	 * 
	 */
	private static DataSource getFileEntryDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception {
		if (DataSourceData.FE_DS == null)
		{
			DataSourceData.provideFileEntryDS(entityTypeDAOService, dataSourceDAOService, em);
		}
		
		return DataSourceData.FE_DS;
	}	
	
	private static DataSource getNoteItemDS(IEntityTypeDAOService entityTypeDAOService,IDataSourceDAOService dataSourceDAOService,EntityManager em) throws Exception {
		if (DataSourceData.NI_DS == null)
		{
			DataSourceData.provideNoteItemDS(entityTypeDAOService, dataSourceDAOService, em);
		}
		
		return DataSourceData.NI_DS;
	}	
}
