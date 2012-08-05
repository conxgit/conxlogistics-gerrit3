package com.conx.logistics.kernel.ui.components.domain.masterdetail;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.kernel.ui.components.domain.BaseComponent;
import com.conx.logistics.kernel.ui.components.domain.databound.DataSourceBoundComponent;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="sysuilineeditorcomponent")
public class LineEditorComponent extends DataSourceBoundComponent {
	@OneToOne
	private BaseComponent mainComponent;
}
