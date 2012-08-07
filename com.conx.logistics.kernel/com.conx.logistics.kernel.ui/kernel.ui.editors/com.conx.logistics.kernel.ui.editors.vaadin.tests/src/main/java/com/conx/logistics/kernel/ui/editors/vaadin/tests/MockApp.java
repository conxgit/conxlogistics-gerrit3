package com.conx.logistics.kernel.ui.editors.vaadin.tests;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.PresenterFactory;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.view.IMultiLevelEntityEditorView;
import com.vaadin.Application;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

@Service
@Transactional
public class MockApp extends Application {
	private static final long serialVersionUID = -5470222303880854277L;

	@Override
	public void init() {
		setTheme("conx");
		
		EventBusManager ebm = new EventBusManager();
		PresenterFactory presenterFactory = new PresenterFactory(ebm, getLocale());
		IPresenter<?, ? extends EventBus> mainPresenter = presenterFactory.createPresenter(MultiLevelEntityEditorPresenter.class);
		MultiLevelEntityEditorEventBus mainEventBus = (MultiLevelEntityEditorEventBus) mainPresenter.getEventBus();
		mainEventBus.start(ebm, presenterFactory);
		
		Window w = new Window("Test Entity Editor App");
		w.setSizeFull();
		w.setLayout((Layout) mainPresenter.getView());
		setMainWindow(w);
		
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
}
