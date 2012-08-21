package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp;

import java.util.HashMap;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.EventBus;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.vaadin.addon.jpacontainer.EntityItem;

public abstract interface AbstractEntityEditorEventBus extends EventBus {
	public abstract void start(AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent lec, EntityManager em, HashMap<String,Object> extraParams);	
	public abstract void entityItemEdit(EntityItem item);
}
