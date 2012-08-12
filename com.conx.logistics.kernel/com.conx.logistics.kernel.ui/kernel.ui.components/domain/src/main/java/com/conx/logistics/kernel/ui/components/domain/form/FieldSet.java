package com.conx.logistics.kernel.ui.components.domain.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXField;
import com.conx.logistics.kernel.ui.components.domain.layout.AbstractConXLayout;

@Entity
public class FieldSet extends AbstractConXComponent {
	@Transient
	private Map<String, DataSourceField> fieldMap = null;

	private int ordinal;

	@OneToOne
	private AbstractConXLayout layout;

	@OneToMany(mappedBy = "fieldSet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<FieldSetField> fields = new ArrayList<FieldSetField>();

	public FieldSet() {
		super("fieldSet");
	}

	public List<FieldSetField> getFields() {
		return fields;
	}

	public void setFields(List<FieldSetField> fields) {
		this.fields = fields;
	}

	public Map<String, DataSourceField> getFieldMap() {
		if (fieldMap == null) {
			fieldMap = new HashMap<String, DataSourceField>();
			for (FieldSetField field : getFields()) {
				fieldMap.put(field.getField().getName(), field.getField());
			}
		}
		return fieldMap;
	}

	public Boolean hasField(String fieldName) {
		return getFieldMap().keySet().contains(fieldName);
	}

	public DataSourceField getField(String fieldName) {
		return getFieldMap().get(fieldName);
	}

	public FieldSet(String caption, int ordinal, List<FieldSetField> fields,
			AbstractConXLayout layout) {
		this(caption,ordinal,layout);
		this.fields = fields;
	}

	public FieldSet(String caption, int ordinal,
			AbstractConXLayout layout) {
		this();
		setCaption(caption);
		this.ordinal = ordinal;
		this.layout = layout;
	}
}
