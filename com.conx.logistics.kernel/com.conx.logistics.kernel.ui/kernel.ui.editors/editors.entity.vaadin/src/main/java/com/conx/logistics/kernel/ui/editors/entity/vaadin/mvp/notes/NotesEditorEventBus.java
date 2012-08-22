package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.notes;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.vaadin.addon.jpacontainer.EntityItem;

public interface NotesEditorEventBus extends AbstractEntityEditorEventBus {
	@Event(handlers = { NotesEditorPresenter.class })
	public void start(AbstractEntityEditorEventBus entityEditorEventListener, AbstractConXComponent aec, EntityManager em);

	@Event(handlers = { NotesEditorPresenter.class })
	public void entityItemEdit(EntityItem item);
}
