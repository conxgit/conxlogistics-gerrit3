package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.vaadin.addon.jpacontainer.EntityItem;

public interface EntityLineEditorEventBus extends AbstractEntityEditorEventBus {
	@Event(handlers = { EntityLineEditorPresenter.class })
	public void start(MultiLevelEntityEditorPresenter parentPresenter, AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em);
	
	
	@Event(handlers = { EntityLineEditorPresenter.class })
	public void start(AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em);
	@Event(handlers = { EntityLineEditorPresenter.class })
	public void entityItemEdit(EntityItem item);
}
