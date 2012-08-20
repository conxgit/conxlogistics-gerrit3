package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp;

import javax.persistence.EntityManager;

import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.eventbus.EventBusManager;
import org.vaadin.mvp.eventbus.annotation.Event;
import org.vaadin.mvp.presenter.PresenterFactory;

import com.conx.logistics.kernel.ui.components.domain.attachment.AttachmentEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.EntityLineEditorSectionPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.table.EntityTablePresenter;
import com.vaadin.addon.jpacontainer.EntityItem;

public interface MultiLevelEntityEditorEventBus extends AbstractEntityEditorEventBus {
	@Event(handlers = { MultiLevelEntityEditorPresenter.class })
	public void init(EventBusManager ebm, PresenterFactory presenterFactory,MasterDetailComponent md, EntityManager em);
	@Event(handlers = { MultiLevelEntityEditorPresenter.class })
	public void start(AbstractEntityEditorEventBus entityEditorEventListener,  LineEditorComponent aec, EntityManager em);
	@Event(handlers = { MultiLevelEntityEditorPresenter.class })
	public void entityItemEdit(EntityItem item);
}
