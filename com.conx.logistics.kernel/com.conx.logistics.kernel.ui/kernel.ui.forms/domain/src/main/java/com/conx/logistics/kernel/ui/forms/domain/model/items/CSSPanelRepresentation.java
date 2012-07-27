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
@Table(name="uiCSSPanelRepresentation")
public class CSSPanelRepresentation extends FormItemRepresentation {

    private String cssClassName;
    private String cssStylesheetUrl;
    private String id;
    private List<FormItemRepresentation> items = new ArrayList<FormItemRepresentation>();
    
    public CSSPanelRepresentation() {
        super("cssPanel");
    }

    public String getCssClassName() {
        return cssClassName;
    }

    public void setCssClassName(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String getCssStylesheetUrl() {
        return cssStylesheetUrl;
    }

    public void setCssStylesheetUrl(String cssStylesheetUrl) {
        this.cssStylesheetUrl = cssStylesheetUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FormItemRepresentation> getItems() {
        return items;
    }

    public void setItems(List<FormItemRepresentation> items) {
        this.items = items;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void setDataMap(Map<String, Object> data) throws FormEncodingException {
        super.setDataMap(data);
        this.cssClassName = (String) data.get("cssClassName");
        this.id = (String) data.get("id");
        this.cssStylesheetUrl = (String) data.get("cssStylesheetUrl");
        this.items.clear();
        List<Object> mapItems = (List<Object>) data.get("items");
        FormRepresentationDecoder decoder = FormEncodingFactory.getDecoder();
        if (mapItems != null) {
            for (Object obj : mapItems) {
                Map<String, Object> itemMap = (Map<String, Object>) obj;
                FormItemRepresentation item = (FormItemRepresentation) decoder.decode(itemMap);
                this.items.add(item);
            }
        }
    }
    
    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("cssClassName", this.cssClassName);
        data.put("cssStylesheetUrl", this.cssStylesheetUrl);
        data.put("id", this.id);
        List<Object> mapItems = new ArrayList<Object>();
        for (FormItemRepresentation item : this.items) {
            mapItems.add(item.getDataMap());
        }
        data.put("items", mapItems);
        return data;
    }
}
