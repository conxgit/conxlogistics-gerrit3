package com.conx.logistics.kernel.ui.components.domain.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXField;
import com.conx.logistics.kernel.ui.components.domain.layout.AbstractConXLayout;

@Entity
public class FieldSet extends AbstractConXComponent {
	@Transient
	private Map<String,AbstractConXField> fieldMap = null;
	
	private int ordinal;
	
	@OneToOne
	private AbstractConXLayout layout;
	
	@OneToMany
	private List<AbstractConXField> fields = new ArrayList<AbstractConXField>();

	
	public FieldSet() {
		super("fieldSet");
	}

	public List<AbstractConXField> getFields() {
		return fields;
	}
	
	public void setFields(List<AbstractConXField> fields) {
		this.fields = fields;
	}

	public Map<String, AbstractConXField> getFieldMap() {
		if (fieldMap == null)
		{
			fieldMap = new HashMap<String, AbstractConXField>();
			for (AbstractConXField field : getFields())
			{
				fieldMap.put(field.getName(), field);
			}
		}
		return fieldMap;
	}
	
	public Boolean hasField(String fieldName)
	{
		return getFieldMap().keySet().contains(fieldName);
	}
	
	public AbstractConXField getField(String fieldName)
	{
		return getFieldMap().get(fieldName);
	}

	public FieldSet(String typeId, int ordinal, 
			        List<AbstractConXField> fields,
			        AbstractConXLayout layout) {
		super(typeId);
		this.ordinal = ordinal;
		this.fields = fields;
		this.layout = layout;
	}
}
