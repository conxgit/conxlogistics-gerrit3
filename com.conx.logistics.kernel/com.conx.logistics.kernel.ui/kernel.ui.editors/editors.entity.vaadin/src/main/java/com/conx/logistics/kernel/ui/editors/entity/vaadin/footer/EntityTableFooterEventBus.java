package com.conx.logistics.kernel.ui.editors.entity.vaadin.footer;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;
import org.vaadin.mvp.presenter.BasePresenter;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.MultiLevelEntityEditorPresenter;

public interface EntityTableFooterEventBus extends EventBus {
  @Event(handlers = { EntityTableFooterPresenter.class })
  public void start(MultiLevelEntityEditorPresenter parentPresenter);
}
