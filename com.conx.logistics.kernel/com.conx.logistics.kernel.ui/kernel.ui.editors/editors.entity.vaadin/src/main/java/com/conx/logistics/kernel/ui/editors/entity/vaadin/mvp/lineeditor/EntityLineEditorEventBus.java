package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;

public interface EntityLineEditorEventBus extends EventBus {
  @Event(handlers = { EntityLineEditorPresenter.class })
  public void start(MultiLevelEntityEditorPresenter parentPresenter);
}
