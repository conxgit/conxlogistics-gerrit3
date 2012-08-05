package com.conx.logistics.kernel.ui.components.domain.databound;

import javax.persistence.MappedSuperclass;

import com.conx.logistics.mdm.domain.MultitenantBaseEntity;
import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.BaseComponent;

@MappedSuperclass
public abstract class DataSourceFieldBoundComponent extends BaseComponent {
	private DataSourceField dataSourceField;
}
