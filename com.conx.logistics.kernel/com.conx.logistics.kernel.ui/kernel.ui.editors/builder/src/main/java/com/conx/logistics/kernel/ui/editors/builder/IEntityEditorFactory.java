package com.conx.logistics.kernel.ui.editors.builder;

import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;

public interface IEntityEditorFactory {
	public Object create(MasterDetailComponent masterDetailComponent);
}
