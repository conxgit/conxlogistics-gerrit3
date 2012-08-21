package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp;

import java.util.HashMap;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.eventbus.annotation.Event;
import org.vaadin.mvp.presenter.PresenterFactory;

import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.vaadin.addon.jpacontainer.EntityItem;

public interface MultiLevelEntityEditorEventBus extends AbstractEntityEditorEventBus {
	@Event(handlers = { MultiLevelEntityEditorPresenter.class })
	public void init(EventBusManager ebm, PresenterFactory presenterFactory,MasterDetailComponent md, EntityManager em, HashMap<String,Object> extraParams);
	
	@Event(handlers = { MultiLevelEntityEditorPresenter.class })
	public void start(AbstractEntityEditorEventBus entityEditorEventListener,  LineEditorComponent aec, EntityManager em, HashMap<String,Object> extraParams);
	@Event(handlers = { MultiLevelEntityEditorPresenter.class })
	public void entityItemEdit(EntityItem item);
}
