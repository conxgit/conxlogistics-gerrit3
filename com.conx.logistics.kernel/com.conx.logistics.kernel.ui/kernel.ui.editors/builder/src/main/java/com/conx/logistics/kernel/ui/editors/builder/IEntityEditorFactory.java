package com.conx.logistics.kernel.ui.editors.builder;

import java.util.Map;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.presenter.IPresenter;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

public interface IEntityEditorFactory {
	public Map<IPresenter<?, ? extends EventBus>,EventBus> create(AbstractConXComponent conXComponent);
}
