package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.eventbus.annotation.Event;
import org.vaadin.mvp.presenter.PresenterFactory;

public interface MultiLevelEntityEditorEventBus extends EventBus {
	@Event(handlers = { MultiLevelEntityEditorPresenter.class })
	public void start(EventBusManager ebm, PresenterFactory presenterFactory);
}
