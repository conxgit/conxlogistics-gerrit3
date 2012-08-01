package com.conx.logistics.kernel.datasource.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.mdm.domain.MultitenantBaseEntity;

@Entity
@Table(name="sysdsdatasource")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public class DataSource extends MultitenantBaseEntity {
	@ManyToOne
	private EntityType entityType;
	
	@OneToMany(mappedBy="dataSource")
	private Set<DataSourceField> dSfields = new HashSet<DataSourceField>();

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	public Set<DataSourceField> getDSfields() {
		return dSfields;
	}

	public void setDSfields(Set<DataSourceField> dSfields) {
		this.dSfields = dSfields;
	}
}
