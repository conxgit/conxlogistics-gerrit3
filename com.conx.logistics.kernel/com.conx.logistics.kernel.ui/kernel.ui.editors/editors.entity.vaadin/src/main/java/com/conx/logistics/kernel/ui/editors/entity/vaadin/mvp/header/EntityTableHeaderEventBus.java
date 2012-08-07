package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;

public interface EntityTableHeaderEventBus extends EventBus {
  @Event(handlers = { EntityTableHeaderPresenter.class })
  public void start(MultiLevelEntityEditorPresenter parentPresenter);
}
