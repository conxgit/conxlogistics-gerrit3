/*

 *



 *

 *





 */
package com.conx.logistics.kernel.ui.forms.domain.model.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.forms.domain.model.FormItemRepresentation;
import com.conx.logistics.kernel.ui.forms.shared.form.FormEncodingException;
import com.conx.logistics.kernel.ui.forms.shared.form.FormEncodingFactory;
import com.conx.logistics.kernel.ui.forms.shared.form.FormRepresentationDecoder;



@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="uiMenuPanelRepresentation")
public class MenuPanelRepresentation extends FormItemRepresentation {

    private String type;
    private String cssClassName;
    private String id;
    private String dir;
    private List<FormItemRepresentation> items = new ArrayList<FormItemRepresentation>();

    public MenuPanelRepresentation() {
        super("menu");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCssClassName() {
        return cssClassName;
    }

    public void setCssClassName(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public List<FormItemRepresentation> getItems() {
        return items;
    }

    public void setItems(List<FormItemRepresentation> items) {
        this.items = items;
    }

    @Override
    public Map<String, Object> getDataMap() {

        Map<String, Object> data = super.getDataMap();
        data.put("cssClassName", this.cssClassName);
        data.put("type", this.type);
        data.put("id", this.id);
        data.put("dir", this.dir);
        List<Object> mapItems = new ArrayList<Object>();
        for (FormItemRepresentation item : this.items) {
            mapItems.add(item.getDataMap());
        }
        data.put("items", mapItems);
        return data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setDataMap(Map<String, Object> data)
            throws FormEncodingException {

        super.setDataMap(data);
        this.cssClassName = (String) data.get("cssClassName");
        this.type = (String) data.get("type");
        this.id = (String) data.get("id");
        this.dir = (String) data.get("dir");
        this.items.clear();
        List<Object> mapItems = (List<Object>) data.get("items");
        FormRepresentationDecoder decoder = FormEncodingFactory.getDecoder();
        if (mapItems != null) {
            for (Object obj : mapItems) {
                Map<String, Object> itemMap = (Map<String, Object>) obj;
                FormItemRepresentation item = (FormItemRepresentation) decoder
                        .decode(itemMap);
                this.items.add(item);
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((cssClassName == null) ? 0 : cssClassName.hashCode());
        result = prime * result + ((dir == null) ? 0 : dir.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MenuPanelRepresentation other = (MenuPanelRepresentation) obj;
        if (cssClassName == null) {
            if (other.cssClassName != null)
                return false;
        } else if (!cssClassName.equals(other.cssClassName))
            return false;
        if (dir == null) {
            if (other.dir != null)
                return false;
        } else if (!dir.equals(other.dir))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
