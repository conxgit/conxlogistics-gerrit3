package com.conx.logistics.kernel.ui.components.domain.tabbed;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;

@Entity
public class DataBoundTabSetComponent extends TabSetComponent {
	@ManyToOne
	private DataSourceField foreignKeyDSField;
	
	@ManyToOne
	private DataSource dataSource;
}
