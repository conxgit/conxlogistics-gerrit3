/*

 *



 *

 *





 */
package com.conx.logistics.kernel.ui.forms.domain.model.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.forms.domain.model.FormItemRepresentation;
import com.conx.logistics.kernel.ui.forms.shared.form.FormEncodingException;



@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="uiSummaryRepresentation")
public class SummaryRepresentation extends FormItemRepresentation {

    private String cssClassName;
    private String dir;
    private String id;
    private int scrollLeft;
    private int scrollTop;
    private List<String> items;
    private Map<String, String> i18n;

    public SummaryRepresentation() {
        super("summary");
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

    public int getScrollLeft() {
        return scrollLeft;
    }

    public void setScrollLeft(int scrollLeft) {
        this.scrollLeft = scrollLeft;
    }

    public int getScrollTop() {
        return scrollTop;
    }

    public void setScrollTop(int scrollTop) {
        this.scrollTop = scrollTop;
    }

    public Map<String, String> getI18n() {
        return i18n;
    }

    public void setI18n(Map<String, String> i18n) {
        this.i18n = i18n;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((cssClassName == null) ? 0 : cssClassName.hashCode());
        result = prime * result + ((dir == null) ? 0 : dir.hashCode());
        result = prime * result + ((i18n == null) ? 0 : i18n.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + scrollLeft;
        result = prime * result + scrollTop;
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
        SummaryRepresentation other = (SummaryRepresentation) obj;
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
        if (i18n == null) {
            if (other.i18n != null)
                return false;
        } else if (!i18n.equals(other.i18n))
            return false;
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (scrollLeft != other.scrollLeft)
            return false;
        if (scrollTop != other.scrollTop)
            return false;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setDataMap(Map<String, Object> data)
            throws FormEncodingException {
        super.setDataMap(data);

        this.cssClassName = (String) data.get("cssClassName");
        this.id = (String) data.get("id");
        this.dir = (String) data.get("dir");
        Map<String, String> i18nMap = (Map<String, String>) data.get("i18n");
        if (i18nMap != null) {
            this.i18n = new HashMap<String, String>();
            this.i18n.putAll(i18nMap);
        }
        List<Object> itemsMap = (List<Object>) data.get("items");
        if (itemsMap != null) {
            this.items = new ArrayList<String>();
            for (Object obj : itemsMap) {
                this.items.add(String.valueOf(obj));
            }
        }
        this.scrollLeft = (Integer) data.get("scrollLeft");
        this.scrollTop = (Integer) data.get("scrollTop");

    }

    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();

        data.put("cssClassName", this.cssClassName);
        data.put("dir", this.dir);
        data.put("id", this.id);
        data.put("scrollLeft", this.scrollLeft);
        data.put("scrollTop", this.scrollTop);
        data.put("i18n", this.i18n);
        data.put("items", this.items);
        return data;
    }
}
