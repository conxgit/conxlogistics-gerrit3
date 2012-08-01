package com.conx.logistics.kernel.ui.editors.domain.model.table;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conx.logistics.kernel.metamodel.domain.AbstractAttribute;
import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.kernel.ui.editors.domain.model.EntityEditorComponentRepresentation;
import com.conx.logistics.mdm.domain.metadata.DefaultEntityMetadata;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="sysedtablecolumnrepresentation")
public class TableColumnRepresentation extends EntityEditorComponentRepresentation {
	@ManyToOne
	private AbstractAttribute entityAttribute;
	
	private Boolean sortable = true;

	private String label;
	
	private String  attributeXPath;//As per http://www.w3.org/TR/xpath/ spec.
	
	private Boolean isNestedAttribute = false;
	
	@OneToOne
	private TableRepresentation parentTable;

	public TableColumnRepresentation(AbstractAttribute entityAttribute,
			Boolean sortable, String label, TableRepresentation parentTable) {
		super();
		this.entityAttribute = entityAttribute;
		this.sortable = sortable;
		this.label = label;
		this.parentTable = parentTable;
	}
}
