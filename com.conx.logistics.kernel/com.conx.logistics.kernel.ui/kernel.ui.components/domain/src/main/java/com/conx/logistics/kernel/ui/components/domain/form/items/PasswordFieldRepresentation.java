package com.conx.logistics.kernel.ui.components.domain.form.items;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.components.domain.form.FormItemRepresentation;
import com.conx.logistics.kernel.ui.components.domain.form.FormEncodingException;



@Entity
public class PasswordFieldRepresentation extends FormItemRepresentation {

    private String defaultValue;
    private String name;
    private String id;
    private Integer maxLength;

    public PasswordFieldRepresentation() {
        super("passwordField");
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("defaultValue", this.defaultValue);
        data.put("name", this.name);
        data.put("id", this.id);
        data.put("maxLength", this.maxLength);
        return data;
    }

    @Override
    public void setDataMap(Map<String, Object> data)
            throws FormEncodingException {
        super.setDataMap(data);
        this.defaultValue = (String) data.get("defaultValue");
        this.name = (String) data.get("name");
        this.id = (String) data.get("id");
        Object obj = data.get("maxLength");
        if (obj != null) {
            this.maxLength = ((Number) obj).intValue();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof PasswordFieldRepresentation))
            return false;
        PasswordFieldRepresentation other = (PasswordFieldRepresentation) obj;
        boolean equals = (this.defaultValue == null && other.defaultValue == null)
                || (this.defaultValue != null && this.defaultValue
                        .equals(other.defaultValue));
        if (!equals)
            return equals;
        equals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        if (!equals)
            return equals;
        equals = (this.id == null && other.id == null)
                || (this.id != null && this.id.equals(other.id));
        if (!equals)
            return equals;
        equals = (this.maxLength == null && other.maxLength == null)
                || (this.maxLength != null && this.maxLength
                        .equals(other.maxLength));
        return equals;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.defaultValue == null ? 0 : this.defaultValue.hashCode();
        result = 37 * result + aux;
        aux = this.name == null ? 0 : this.name.hashCode();
        result = 37 * result + aux;
        aux = this.id == null ? 0 : this.id.hashCode();
        result = 37 * result + aux;
        aux = this.maxLength == null ? 0 : this.maxLength.hashCode();
        result = 37 * result + aux;
        return result;
    }
}
