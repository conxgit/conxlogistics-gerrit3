package com.conx.logistics.kernel.system.ui.view;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.VerticalLayout;

public interface IControlPanelView {

  public abstract void setNavigation(Component menu);

  public abstract void setContent(Component content);

  public abstract HorizontalSplitPanel getSplitLayout();

  public abstract VerticalLayout getMainLayout();

}