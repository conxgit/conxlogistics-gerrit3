package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.attachment;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.annotation.Event;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.vaadin.addon.jpacontainer.EntityItem;

public interface AttachmentEditorEventBus extends AbstractEntityEditorEventBus {
	@Event(handlers = { AttachmentEditorPresenter.class })
	public void start(AbstractEntityEditorEventBus entityEditorEventListener, AbstractConXComponent aec, EntityManager em);

	@Event(handlers = { AttachmentEditorPresenter.class })
	public void entityItemEdit(EntityItem item);
}
