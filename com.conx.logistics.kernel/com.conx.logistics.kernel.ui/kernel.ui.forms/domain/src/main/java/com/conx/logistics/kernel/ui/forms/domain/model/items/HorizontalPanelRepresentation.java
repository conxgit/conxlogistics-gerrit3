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
@Table(name="uiHorizontalPanelRepresentation")
public class HorizontalPanelRepresentation extends FormItemRepresentation {

    private Integer borderWidth;
    private Integer spacing;
    private String cssClassName;
    private String horizontalAlignment;
    private String verticalAlignment;
    private String title;
    private String id;
    private List<FormItemRepresentation> items = new ArrayList<FormItemRepresentation>();
    
    public HorizontalPanelRepresentation() {
        super("horizontalPanel");
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    public Integer getSpacing() {
        return spacing;
    }

    public void setSpacing(Integer spacing) {
        this.spacing = spacing;
    }

    public String getCssClassName() {
        return cssClassName;
    }

    public void setCssClassName(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public String getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<FormItemRepresentation> getItems() {
        return items;
    }
    
    public void addItem(FormItemRepresentation item) {
        items.add(item);
    }
    
    public void setItems(List<FormItemRepresentation> items) {
        this.items = items;
    }
    
    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("borderWidth", this.borderWidth);
        data.put("spacing", this.spacing);
        data.put("cssClassName", this.cssClassName);
        data.put("horizontalAlignment", this.horizontalAlignment);
        data.put("verticalAlignment", this.verticalAlignment);
        data.put("title", this.title);
        data.put("id", this.id);
        List<Map<String, Object>> mapItems = new ArrayList<Map<String, Object>>();
        if (this.items != null) {
            for (FormItemRepresentation item : this.items) {
                mapItems.add(item == null ? null : item.getDataMap());
            }
        }
        data.put("items", mapItems);
        return data;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void setDataMap(Map<String, Object> data) throws FormEncodingException {
        super.setDataMap(data);
        this.borderWidth = data.get("borderWidth") == null ? null : ((Number) data.get("borderWidth")).intValue();
        this.spacing = data.get("spacing") == null ? null : ((Number) data.get("spacing")).intValue();
        this.cssClassName = (String) data.get("cssClassName");
        this.horizontalAlignment = (String) data.get("horizontalAlignment");
        this.verticalAlignment = (String) data.get("verticalAlignment");
        this.title = (String) data.get("title");
        this.id = (String) data.get("id");
        this.items.clear();
        FormRepresentationDecoder decoder = FormEncodingFactory.getDecoder();
        List<Map<String, Object>> mapItems = (List<Map<String, Object>>) data.get("items");
        if (mapItems != null) {
            for (Map<String, Object> mapItem : mapItems) {
                this.items.add((FormItemRepresentation) decoder.decode(mapItem));
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof TableRepresentation)) return false;
        HorizontalPanelRepresentation other = (HorizontalPanelRepresentation) obj;
        boolean equals = (this.borderWidth == null && other.borderWidth == null) || 
            (this.borderWidth != null && this.borderWidth.equals(other.borderWidth));
        if (!equals) return equals;
        equals = (this.spacing == null && other.spacing == null) || (this.spacing != null && this.spacing.equals(other.spacing));
        if (!equals) return equals;
        equals = (this.cssClassName == null && other.cssClassName == null) || 
            (this.cssClassName != null && this.cssClassName.equals(other.cssClassName));
        if (!equals) return equals;
        equals = (this.horizontalAlignment == null && other.horizontalAlignment == null) || 
            (this.horizontalAlignment != null && this.horizontalAlignment.equals(other.horizontalAlignment));
        if (!equals) return equals;
        equals = (this.verticalAlignment == null && other.verticalAlignment == null) || 
            (this.verticalAlignment != null && this.verticalAlignment.equals(other.verticalAlignment));
        if (!equals) return equals;
        equals = (this.title == null && other.title == null) || (this.title != null && this.title.equals(other.title));
        if (!equals) return equals;
        equals = (this.id == null && other.id == null) || (this.id != null && this.id.equals(other.id));
        if (!equals) return equals;
        equals = (this.items == null && other.items == null) || (this.items != null && this.items.equals(other.items));
        return equals;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.borderWidth == null ? 0 : this.borderWidth.hashCode();
        result = 37 * result + aux;
        aux = this.spacing == null ? 0 : this.spacing.hashCode();
        result = 37 * result + aux;
        aux = this.cssClassName == null ? 0 : this.cssClassName.hashCode();
        result = 37 * result + aux;
        aux = this.horizontalAlignment == null ? 0 : this.horizontalAlignment.hashCode();
        result = 37 * result + aux;
        aux = this.verticalAlignment == null ? 0 : this.verticalAlignment.hashCode();
        result = 37 * result + aux;
        aux = this.title == null ? 0 : this.title.hashCode();
        result = 37 * result + aux;
        aux = this.id == null ? 0 : this.id.hashCode();
        result = 37 * result + aux;
        aux = this.items == null ? 0 : this.items.hashCode();
        result = 37 * result + aux;
        return result;
    }

}
