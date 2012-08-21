package com.conx.logistics.kernel.ui.editors.builder.vaadin;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.PresenterFactory;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.attachment.AttachmentEditorComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.ConfigurablePresenterFactory;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.attachment.AttachmentEditorPresenter;
import com.conx.logistics.kernel.ui.factory.services.IEntityEditorFactory;

public class VaadinEntityEditorFactoryImpl implements
		IEntityEditorFactory {

	private ConfigurablePresenterFactory factory;


	public VaadinEntityEditorFactoryImpl(ConfigurablePresenterFactory factory) {
		this.factory = factory;
	}

	@Override
	public Map<IPresenter<?, ? extends EventBus>,EventBus> create(AbstractConXComponent conXComponent) {
		Map<IPresenter<?, ? extends EventBus>,EventBus> res = null;
		
		if (conXComponent instanceof AttachmentEditorComponent)
		{
			IPresenter<?, ? extends EventBus> presenter = factory.createPresenter(AttachmentEditorPresenter.class);
			EventBus eventBus = presenter.getEventBus();
			
			res = new HashMap<IPresenter<?,? extends EventBus>, EventBus>();
			res.put(presenter, eventBus);
		}
		return res;
	}

}
