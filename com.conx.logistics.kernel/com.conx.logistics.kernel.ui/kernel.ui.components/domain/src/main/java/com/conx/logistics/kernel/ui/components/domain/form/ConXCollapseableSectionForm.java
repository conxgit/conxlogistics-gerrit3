package com.conx.logistics.kernel.ui.components.domain.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXField;
import com.conx.logistics.kernel.ui.components.domain.layout.AbstractConXLayout;

@Entity
public class ConXCollapseableSectionForm extends ConXForm {
	
	@Transient
	private Map<String,FieldSet> fieldSetMap = null;
	
	@Transient
	private Map<String,AbstractConXField> fieldMap = null;	

	
	@OneToMany
	private List<FieldSet> fieldSetList = new ArrayList<FieldSet>();
	
	
	public ConXCollapseableSectionForm() {
		super("collapseableSectionForm");
	}


	public List<FieldSet> getFieldSetList() {
		return fieldSetList;
	}
	
	public Map<String, FieldSet> getFieldSetMap() {
		if (fieldSetMap == null)
		{
			fieldSetMap = new HashMap<String, FieldSet>();
			Map<String, AbstractConXField> fm;
			for (FieldSet fieldSet : getFieldSetList())
			{
				fm = fieldSet.getFieldMap();
				for (String fieldName : fm.keySet())
				{
					fieldSetMap.put(fieldName, fieldSet);
				}
			}
		}
		return fieldSetMap;
	}	
	
	public Map<String, AbstractConXField> getFieldMap() {
		if (fieldMap == null)
		{
			fieldMap = new HashMap<String, AbstractConXField>();
			Map<String, AbstractConXField> fm;
			for (FieldSet fieldSet : getFieldSetList())
			{
				fm = fieldSet.getFieldMap();
				fieldMap.putAll(fm);
			}
		}
		return fieldMap;
	}		
	
	public FieldSet getFieldSetForField(String fieldName)
	{
		return getFieldSetMap().get(fieldName);
	}
	
	public AbstractConXField getField(String fieldName)
	{
		return getFieldMap().get(fieldName);
	}	


	public void setFieldSetList(List<FieldSet> fieldSetList) {
		this.fieldSetList = fieldSetList;
	}


	public ConXCollapseableSectionForm(String typeId,
			AbstractConXLayout layout, List<FieldSet> fieldSetList) {
		super(typeId);
		setLayout(layout);
		this.fieldSetList = fieldSetList;
	}
}
