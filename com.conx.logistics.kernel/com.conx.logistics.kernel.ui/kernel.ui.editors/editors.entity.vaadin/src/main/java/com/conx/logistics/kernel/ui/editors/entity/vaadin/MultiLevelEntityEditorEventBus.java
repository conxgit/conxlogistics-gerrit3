package com.conx.logistics.kernel.ui.editors.entity.vaadin;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;
import org.vaadin.mvp.presenter.BasePresenter;

public interface MultiLevelEntityEditorEventBus extends EventBus {
  @Event(handlers = { MultiLevelEntityEditorPresenter.class })
  public void start();
}
