package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.EntityLineEditorSectionPresenter;
import com.vaadin.addon.jpacontainer.EntityItem;

public interface EntityTableEventBus extends EventBus {
	@Event(handlers = { EntityTablePresenter.class })
	public void start(AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em);
	@Event(handlers = { EntityTablePresenter.class })
	public void entityItemEdit(EntityItem item);
}
