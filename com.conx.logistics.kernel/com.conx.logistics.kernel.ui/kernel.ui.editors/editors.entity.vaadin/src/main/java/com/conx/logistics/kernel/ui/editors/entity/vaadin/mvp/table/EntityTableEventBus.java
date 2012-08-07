package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;

public interface EntityTableEventBus extends EventBus {
  @Event(handlers = { EntityTablePresenter.class })
  public void start(MultiLevelEntityEditorPresenter parentPresenter);
}
