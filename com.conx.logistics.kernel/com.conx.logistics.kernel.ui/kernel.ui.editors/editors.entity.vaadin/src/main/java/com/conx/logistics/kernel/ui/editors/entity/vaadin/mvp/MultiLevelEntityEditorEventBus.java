package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.eventbus.annotation.Event;
import org.vaadin.mvp.presenter.PresenterFactory;

import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;

public interface MultiLevelEntityEditorEventBus extends EventBus {
	@Event(handlers = { MultiLevelEntityEditorPresenter.class })
	public void start(EventBusManager ebm, PresenterFactory presenterFactory,MasterDetailComponent md, EntityManager em);
}
