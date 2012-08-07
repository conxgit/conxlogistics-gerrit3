package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.footer;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;

public interface EntityTableFooterEventBus extends EventBus {
  @Event(handlers = { EntityTableFooterPresenter.class })
  public void start(MultiLevelEntityEditorPresenter parentPresenter);
}
