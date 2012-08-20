package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.header;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.EntityLineEditorSectionPresenter;
import com.vaadin.addon.jpacontainer.EntityItem;

public interface EntityTableHeaderEventBus extends AbstractEntityEditorEventBus {
	@Event(handlers = { EntityTableHeaderPresenter.class })
	public void start(AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em);
	@Event(handlers = { EntityTableHeaderPresenter.class })
	public void entityItemEdit(EntityItem item);
}
