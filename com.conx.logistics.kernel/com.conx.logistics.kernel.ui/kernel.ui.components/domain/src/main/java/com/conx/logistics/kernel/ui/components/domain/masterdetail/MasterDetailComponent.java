package com.conx.logistics.kernel.ui.components.domain.masterdetail;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.kernel.ui.components.domain.databound.DataSourceBoundComponent;
import com.conx.logistics.kernel.ui.components.domain.table.TableComponent;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="sysuimasterdetailcomponent")
public class MasterDetailComponent extends DataSourceBoundComponent {
	@ManyToOne
	private DataSource dataSource;
	
	@ManyToOne
	private TableComponent table;	
	
	@OneToMany
	private LineEditorContainerComponent lineEditorPanel; 
}
