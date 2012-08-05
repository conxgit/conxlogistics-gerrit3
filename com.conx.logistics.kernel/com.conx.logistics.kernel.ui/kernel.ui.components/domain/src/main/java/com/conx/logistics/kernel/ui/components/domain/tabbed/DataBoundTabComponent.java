package com.conx.logistics.kernel.ui.components.domain.tabbed;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import com.conx.logistics.kernel.datasource.domain.DataSource;

@Entity
public class DataBoundTabComponent extends TabComponent {
	@ManyToOne
	private DataSource dataSource;
}
