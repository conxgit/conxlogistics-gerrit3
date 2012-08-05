package com.conx.logistics.kernel.ui.components.domain.form.items;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.components.domain.form.FormItemRepresentation;
import com.conx.logistics.kernel.ui.components.domain.form.FormEncodingException;



@Entity
public class LabelRepresentation extends FormItemRepresentation {

    private String value;
    private String id;
    private String cssName;
    private Map<String, String> i18n;
    private String format;

    public LabelRepresentation() {
        super("label");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCssName() {
        return cssName;
    }

    public void setCssName(String cssName) {
        this.cssName = cssName;
    }

    public Map<String, String> getI18n() {
        return i18n;
    }

    public void setI18n(Map<String, String> i18n) {
        this.i18n = i18n;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("value", this.value);
        data.put("id", this.id);
        data.put("cssName", this.cssName);
        data.put("i18n", this.i18n);
        data.put("format", this.format);
        return data;
    }

    @Override
    public void setDataMap(Map<String, Object> data)
            throws FormEncodingException {
        super.setDataMap(data);
        this.value = (String) data.get("value");
        this.id = (String) data.get("id");
        this.cssName = (String) data.get("cssName");
        @SuppressWarnings("unchecked")
        Map<String, String> i18nMap = (Map<String, String>) data.get("i18n");
        if (i18nMap != null) {
            this.i18n = new HashMap<String, String>();
            this.i18n.putAll(i18nMap);
        }
        this.format = (String) data.get("format");
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof LabelRepresentation))
            return false;
        LabelRepresentation other = (LabelRepresentation) obj;
        boolean equals = (this.value == null && other.value == null)
                || (this.value != null && this.value.equals(other.value));
        if (!equals)
            return equals;
        equals = (this.id == null && other.id == null)
                || (this.id != null && this.id.equals(other.id));
        if (!equals)
            return equals;
        equals = (this.cssName == null && other.cssName == null)
                || (this.cssName != null && this.cssName.equals(other.cssName));
        if (!equals)
            return equals;
        equals = (this.i18n == null && other.i18n == null)
                || (this.i18n != null && this.i18n.entrySet().equals(
                        other.i18n.entrySet()));
        if (!equals)
            return equals;
        equals = (this.format == null && other.format == null)
                || (this.format != null && this.format.equals(other.format));
        return equals;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.value == null ? 0 : this.value.hashCode();
        result = 37 * result + aux;
        aux = this.id == null ? 0 : this.id.hashCode();
        result = 37 * result + aux;
        aux = this.cssName == null ? 0 : this.cssName.hashCode();
        result = 37 * result + aux;
        aux = this.i18n == null ? 0 : this.i18n.hashCode();
        result = 37 * result + aux;
        aux = this.format == null ? 0 : this.format.hashCode();
        result = 37 * result + aux;
        return result;
    }
}
