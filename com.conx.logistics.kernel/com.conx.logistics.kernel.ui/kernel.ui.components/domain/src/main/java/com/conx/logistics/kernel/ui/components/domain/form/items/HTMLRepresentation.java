package com.conx.logistics.kernel.ui.components.domain.form.items;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.components.domain.form.FormItemRepresentation;
import com.conx.logistics.kernel.ui.components.domain.form.FormEncodingException;



@Entity
public class HTMLRepresentation extends FormItemRepresentation {

    private String content;

    public HTMLRepresentation() {
        super("html");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("content", this.content);
        return data;
    }

    @Override
    public void setDataMap(Map<String, Object> data)
            throws FormEncodingException {
        super.setDataMap(data);
        this.content = (String) data.get("content");
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof HTMLRepresentation))
            return false;
        HTMLRepresentation other = (HTMLRepresentation) obj;
        return (this.content == null && other.content == null)
                || (this.content != null && this.content.equals(other.content));
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.content == null ? 0 : this.content.hashCode();
        result = 37 * result + aux;
        return result;
    }
}
