/*

 *



 *

 *





 */
package com.conx.logistics.kernel.ui.forms.domain.model;

import java.util.HashMap;
import java.util.Map;




public class ExternalData implements Mappable {

    private String source;
    private String method;
    private String responseLanguage;
    private String xpath;

    
    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("@className", getClass().getName());
        data.put("source", this.source);
        data.put("method", this.method);
        data.put("responseLanguage", this.responseLanguage);
        data.put("xpath", this.xpath);
        return data;
    }

    @Override
    public void setDataMap(Map<String, Object> dataMap) {
        this.source = (String) dataMap.get("source");
        this.method = (String) dataMap.get("method");
        this.responseLanguage = (String) dataMap.get("responseLanguage");
        this.xpath = (String) dataMap.get("xpath");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof ExternalData)) return false;
        ExternalData other = (ExternalData) obj;
        
        boolean equals = (this.source == null && other.source == null) || (this.source != null && this.source.equals(other.source));
        if (!equals) return equals;
        equals = (this.method == null && other.method == null) || (this.method != null && this.method.equals(other.method));
        if (!equals) return equals;
        equals = (this.responseLanguage == null && other.responseLanguage == null) || 
            (this.responseLanguage != null && this.responseLanguage.equals(other.responseLanguage));
        if (!equals) return equals;
        equals = (this.xpath == null && other.xpath == null) || (this.xpath != null && this.xpath.equals(other.xpath));
        return equals;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.source == null ? 0 : this.source.hashCode();
        result = 37 * result + aux;
        aux = this.method == null ? 0 : this.method.hashCode();
        result = 37 * result + aux;
        aux = this.responseLanguage == null ? 0 : this.responseLanguage.hashCode();
        result = 37 * result + aux;
        aux = this.xpath == null ? 0 : this.xpath.hashCode();
        result = 37 * result + aux;
        return result;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResponseLanguage() {
        return responseLanguage;
    }

    public void setResponseLanguage(String responseLanguage) {
        this.responseLanguage = responseLanguage;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }
}
