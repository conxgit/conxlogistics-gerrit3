package com.conx.logistics.kernel.ui.components.domain.form.items;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.components.domain.form.FormItemRepresentation;
import com.conx.logistics.kernel.ui.components.domain.form.FormEncodingException;


@Entity
public class RichTextEditorRepresentation extends FormItemRepresentation {

    private String html;
    
    public RichTextEditorRepresentation() {
        super("richTextEditor");
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    
    @Override
    public void setDataMap(Map<String, Object> data) throws FormEncodingException {
        this.html = (String) data.get("html");
        super.setDataMap(data);
    }
    
    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("html", this.html);
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof RichTextEditorRepresentation)) return false;
        RichTextEditorRepresentation other = (RichTextEditorRepresentation) obj;
        boolean equals = (this.html == null && other.html == null) || (this.html != null && this.html.equals(other.html));
        return equals;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.html == null ? 0 : this.html.hashCode();
        result = 37 * result + aux;
        return result;
    }
}
