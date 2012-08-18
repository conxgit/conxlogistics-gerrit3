package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;

public interface EntityLineEditorSectionEventBus extends EventBus {
  @Event(handlers = { EntityLineEditorSectionPresenter.class })
  public void start(EntityLineEditorPresenter parentPresenter, LineEditorComponent lineEditorSectionComponentModel);
}
