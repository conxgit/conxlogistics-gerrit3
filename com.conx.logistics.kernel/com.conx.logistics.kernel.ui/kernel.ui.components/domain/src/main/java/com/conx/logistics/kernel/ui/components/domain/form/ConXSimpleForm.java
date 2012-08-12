package com.conx.logistics.kernel.ui.components.domain.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXField;
import com.conx.logistics.kernel.ui.components.domain.layout.AbstractConXLayout;

@Entity
public class ConXSimpleForm extends ConXForm {

	@Transient
	private Map<String,DataSourceField> fieldMap = null;	

	
	@OneToMany
	private List<FieldSet> fieldSetList = new ArrayList<FieldSet>();
	
	
	public ConXSimpleForm() {
		super("simpleForm");
	}
	
	public List<FieldSet> getFieldSetList() {
		return fieldSetList;
	}


	public Map<String, DataSourceField> getFieldMap() {
		if (fieldMap == null)
		{
			fieldMap = new HashMap<String, DataSourceField>();
			Map<String, DataSourceField> fm;
			for (FieldSet fieldSet : getFieldSetList())
			{
				fm = fieldSet.getFieldMap();
				fieldMap.putAll(fm);
			}
		}
		return fieldMap;
	}		

	
	public DataSourceField getField(String fieldName)
	{
		return getFieldMap().get(fieldName);
	}	


	public void setFieldSetList(List<FieldSet> fieldSetList) {
		this.fieldSetList = fieldSetList;
	}


	public ConXSimpleForm(DataSource ds,List<FieldSet> fieldSetList) {
		this();
		this.fieldSetList = fieldSetList;
	}
}
