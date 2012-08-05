package com.conx.logistics.kernel.ui.components.domain.table;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conx.logistics.kernel.metamodel.domain.AbstractAttribute;
import com.conx.logistics.kernel.ui.components.domain.databound.DataSourceFieldBoundComponent;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="sysuitablecolumncomponent")
public class TableColumnComponent extends DataSourceFieldBoundComponent {
	@OneToOne
	private TableColumnComponent parentTable;

	public TableColumnComponent(AbstractAttribute entityAttribute,
			Boolean sortable, String label, TableColumnComponent parentTable) {
		super();
	}
}
