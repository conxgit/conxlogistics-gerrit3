package com.conx.logistics.kernel.system.ui.navigation;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.common.mvp.MainMVPApplication;

public interface ControlPanelNavigationEventBus extends EventBus {
  @Event(handlers = { ControlPanelNavigationPresenter.class })
  public void start(MainMVPApplication app); 
}
