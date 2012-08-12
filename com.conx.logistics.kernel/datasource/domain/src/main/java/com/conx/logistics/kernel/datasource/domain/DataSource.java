package com.conx.logistics.kernel.datasource.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.mdm.domain.MultitenantBaseEntity;

@Entity
@Table(name="sysdsdatasource")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public class DataSource extends MultitenantBaseEntity {
	@Transient
	private Set<String> visibleFieldNames = null;
	
	@Transient
	private Set<String> nestedFieldNames = null;
	
	
	@Transient
	private Map<String,DataSourceField> dataSourceFieldMap = null;	
	
	@ManyToOne
	private EntityType entityType;
	
	@OneToMany(mappedBy="parentDataSource",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<DataSourceField> dSfields = new HashSet<DataSourceField>();

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	public Set<DataSourceField> getDSFields() {
		return dSfields;
	}
	
	public DataSource() {
	}

	public void setDSFields(Set<DataSourceField> dSfields) {
		this.dSfields = dSfields;
	}

	public DataSource(String code, EntityType entityType) {
		super();
		setCode(code);
		setName(code);
		this.entityType = entityType;
	}
	
	public Set<String> getNestedFieldNames()
	{
		getVisibleFieldNames();
		return nestedFieldNames;
	}
	
	public Set<String> getVisibleFieldNames()
	{
		if (visibleFieldNames == null)
		{
			visibleFieldNames = new HashSet<String>();
			nestedFieldNames = new HashSet<String>();
			Set<DataSourceField> dsFields = getDSFields();
			String derivedFieldName;
			for (DataSourceField dsf : dsFields)
			{
				if (!dsf.getHidden())
				{
					if (dsf.isNestedAttribute())
					{
						derivedFieldName = dsf.getJPAPath();
						nestedFieldNames.add(derivedFieldName);
						visibleFieldNames.add(derivedFieldName);
					}
					else
					{
						derivedFieldName = dsf.getName();
						visibleFieldNames.add(dsf.getName());
					}
				}
			}
		}
		return visibleFieldNames;
	}	
	
	
	public DataSourceField getField(String dsFieldName)
	{
		if (dataSourceFieldMap == null)
		{
			dataSourceFieldMap = new HashMap<String, DataSourceField>();
			Set<DataSourceField> dsFields = getDSFields();
			for (DataSourceField dsf : dsFields)
			{
				dataSourceFieldMap.put(dsf.getName(), dsf);
			}
		}
		return dataSourceFieldMap.get(dsFieldName);
	}		
	
	
	public String toString()
	{
		int indent = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append(indent(indent)+"{\n");
		
		sb.append(indent(indent+1)+"name: "+getName());
		sb.append(indent(indent+1)+"type: "+getEntityType().getJpaEntityName());
		
		sb.append(indent(indent+1)+"fields : [\n");
		
		for (DataSourceField dsf : getDSFields())
		{
			sb.append(indent(indent+2)+dsf.toString()+";");
		}
		
		sb.append(indent(indent+1)+"]");
		
		sb.append(indent(indent)+"}\n");
		
		return sb.toString();
	}

	static String indent(int indent) {
		String res = "";
		for (int i = 0; i<indent; i++)
			res += res+"\t";
		
		return res;
	}
}
