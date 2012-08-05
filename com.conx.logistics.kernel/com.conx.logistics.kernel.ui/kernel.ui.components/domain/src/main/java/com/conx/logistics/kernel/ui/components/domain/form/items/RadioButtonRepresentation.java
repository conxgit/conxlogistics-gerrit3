package com.conx.logistics.kernel.ui.components.domain.form.items;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.components.domain.form.FormItemRepresentation;
import com.conx.logistics.kernel.ui.components.domain.form.FormEncodingException;


@Entity
public class RadioButtonRepresentation extends FormItemRepresentation {

    private String name;
    private String id;
    private String value;
    private Boolean selected = Boolean.FALSE;
       
    public RadioButtonRepresentation() {
        super("radioButton");
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    
    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("name", this.name);
        data.put("id", this.id);
        data.put("value", this.value);
        data.put("selected", this.selected);
        return data;
    }
    
    @Override
    public void setDataMap(Map<String, Object> data) throws FormEncodingException {
        super.setDataMap(data);
        this.name = (String) data.get("name");
        this.id = (String) data.get("id");
        this.value = (String) data.get("value");
        this.selected = (Boolean) data.get("selected");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof RadioButtonRepresentation)) return false;
        RadioButtonRepresentation other = (RadioButtonRepresentation) obj;
        boolean equals = (this.name == null && other.name == null) || (this.name != null && this.name.equals(other.name));
        if (!equals) return equals;
        equals = (this.id == null && other.id == null) || (this.id != null && this.id.equals(other.id));
        if (!equals) return equals;
        equals = (this.value == null && other.value == null) || (this.value != null && this.value.equals(other.value));
        if (!equals) return equals;
        equals = (this.selected == null && other.selected == null) || (this.selected != null && this.selected.equals(other.selected));
        return equals;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.name == null ? 0 : this.name.hashCode();
        result = 37 * result + aux;
        aux = this.id == null ? 0 : this.id.hashCode();
        result = 37 * result + aux;
        aux = this.value == null ? 0 : this.value.hashCode();
        result = 37 * result + aux;
        aux = this.selected == null ? 0 : this.selected.hashCode();
        result = 37 * result + aux;
        return result;
    }
}
