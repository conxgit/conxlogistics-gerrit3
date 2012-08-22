package com.conx.logistics.kernel.ui.components.domain.form;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;

@Entity
public class ConXSimpleForm extends ConXForm {

	@Transient
	private Map<String,DataSourceField> fieldMap = null;	

	
	@OneToMany(mappedBy="form")
	private Set<FormField> formFieldList = new HashSet<FormField>();
	
	
	public ConXSimpleForm(DataSource ds, Set<FormField> formFieldList) {
		this();
		setDataSource(ds);
		this.formFieldList = formFieldList;
	}	
	
	public ConXSimpleForm() {
		super("simpleForm");
	}
	
	public Set<FormField> getFormFieldList() {
		return formFieldList;
	}


	public Map<String, DataSourceField> getFieldMap() {
		if (fieldMap == null)
		{
			fieldMap = new HashMap<String, DataSourceField>();
			Map<String, DataSourceField> fm;
			for (FormField formField : getFormFieldList())
			{
				fieldMap.put(formField.getField().getName(),formField.getField());
			}
		}
		return fieldMap;
	}		

	
	public DataSourceField getField(String fieldName)
	{
		return getFieldMap().get(fieldName);
	}

	public void setFieldMap(Map<String, DataSourceField> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public void setFormFieldList(Set<FormField> formFieldList) {
		this.formFieldList = formFieldList;
	}	

}
