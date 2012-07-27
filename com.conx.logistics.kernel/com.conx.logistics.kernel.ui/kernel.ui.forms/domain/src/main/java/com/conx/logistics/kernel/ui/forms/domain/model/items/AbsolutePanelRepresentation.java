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
import com.conx.logistics.kernel.ui.forms.shared.form.FormEncodingFactory;
import com.conx.logistics.kernel.ui.forms.shared.form.FormRepresentationDecoder;



@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="uiabsolutepanelrepresentation")
public class AbsolutePanelRepresentation extends FormItemRepresentation {

    public static class Position {
        private final int x;
        private final int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
    }
    
    private Map<Position, FormItemRepresentation> items = new HashMap<Position, FormItemRepresentation>();


    public AbsolutePanelRepresentation() {
        super("absolutePanel");
    }

    public void addItem(FormItemRepresentation rep, int x, int y) {
        items.put(new Position(x, y), rep);
    }
    
    public Map<Position, FormItemRepresentation> getItems() {
        return items;
    }
    
    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("id", this.id);
        List<Map<String, Object>> mapItems = new ArrayList<Map<String, Object>>();
        if (this.items != null) {
            for (Map.Entry<Position, FormItemRepresentation> entry : this.items.entrySet()) {
                FormItemRepresentation item = entry.getValue();
                Position pos = entry.getKey();
                Map<String, Object> itemData = item == null ? null : item.getDataMap();
                itemData.put("x", pos.getX());
                itemData.put("y", pos.getY());
                mapItems.add(itemData);
            }
        }
        data.put("items", mapItems);
        return data;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void setDataMap(Map<String, Object> data) throws FormEncodingException {
        super.setDataMap(data);
        this.items.clear();
        List<Map<String, Object>> mapItems = (List<Map<String, Object>>) data.get("items");
        FormRepresentationDecoder decoder = FormEncodingFactory.getDecoder();
        if (mapItems != null) {
            for (Map<String, Object> entry : mapItems) {
                int x = entry.get("x") == null ? 0 : ((Number) entry.get("x")).intValue();
                int y = entry.get("y") == null ? 0 : ((Number) entry.get("y")).intValue();
                Position pos = new Position(x, y);
                FormItemRepresentation item = (FormItemRepresentation) decoder.decode(entry);
                this.items.put(pos, item);
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof AbsolutePanelRepresentation)) return false;
        AbsolutePanelRepresentation other = (AbsolutePanelRepresentation) obj;
        boolean equals = (this.items == null && other.items == null) || 
            (this.items != null && other.items != null && this.items.entrySet().equals(other.items.entrySet()));
        if (!equals) return equals;
        equals = (this.id == null && other.id == null) || (this.id != null && this.id.equals(other.id));
        return equals;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.items == null ? 0 : this.items.hashCode();
        result = 37 * result + aux;
        aux = this.id == null ? 0 : this.id.hashCode();
        result = 37 * result + aux;
        return result;
    }

}
