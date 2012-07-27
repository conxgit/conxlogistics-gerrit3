/*

 *



 *

 *





 */
package com.conx.logistics.kernel.ui.forms.domain.model.items;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.forms.domain.model.FormItemRepresentation;
import com.conx.logistics.kernel.ui.forms.shared.form.FormEncodingException;



@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="uiUploadWithProgressBarRepresentation")
public class UploadWithProgressBarRepresentation extends FormItemRepresentation {

    private boolean autoSubmit;
    private boolean avoidRepeatFiles;
    private boolean enabled;
    private String cssClassName;
    
    public UploadWithProgressBarRepresentation() {
        super("uploadWithProgressBar");
    }

    public boolean isAutoSubmit() {
        return autoSubmit;
    }

    public void setAutoSubmit(boolean autoSubmit) {
        this.autoSubmit = autoSubmit;
    }

    public boolean isAvoidRepeatFiles() {
        return avoidRepeatFiles;
    }

    public void setAvoidRepeatFiles(boolean avoidRepeatFiles) {
        this.avoidRepeatFiles = avoidRepeatFiles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCssClassName() {
        return cssClassName;
    }

    public void setCssClassName(String cssClassName) {
        this.cssClassName = cssClassName;
    }
    
    @Override
    public void setDataMap(Map<String, Object> data)
            throws FormEncodingException {
        super.setDataMap(data);
        this.autoSubmit = extractBoolean(data.get("autoSubmit"));
        this.avoidRepeatFiles = extractBoolean(data.get("avoidRepeatFiles"));
        this.enabled = extractBoolean(data.get("enabled"));
        this.cssClassName = (String) data.get("cssClassName");
    }
    
    private boolean extractBoolean(Object aux) {
        if (aux == null) {
            return false;
        }
        if (aux instanceof String) {
            String saux = (String) aux;
            if ("".equals(saux)) {
                return false;
            }
            return Boolean.valueOf(saux);
        }
        if (aux instanceof Boolean) {
            Boolean baux = (Boolean) aux;
            return baux.booleanValue();
        }
        return false;
    }

    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("autoSubmit", Boolean.valueOf(this.autoSubmit));
        data.put("avoidRepeatFiles", Boolean.valueOf(this.avoidRepeatFiles));
        data.put("enabled", Boolean.valueOf(this.enabled));
        data.put("cssClassName", this.cssClassName);
        return data;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof UploadWithProgressBarRepresentation)) return false;
        UploadWithProgressBarRepresentation other = (UploadWithProgressBarRepresentation) obj;
        boolean equals = (this.autoSubmit == other.autoSubmit);
        if (!equals) return equals;
        equals = (this.avoidRepeatFiles == other.avoidRepeatFiles);
        if (!equals) return equals;
        equals = this.enabled == other.enabled;
        if (!equals) return equals;
        equals = (this.cssClassName == null && other.cssClassName == null) || (this.cssClassName != null && this.cssClassName.equals(other.cssClassName)); 
        return equals;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.autoSubmit ? 0 : 1;
        result = 37 * result + aux;
        aux = this.avoidRepeatFiles ? 0 : 1;
        result = 37 * result + aux;
        aux = this.enabled ? 0 : 1;
        result = 37 * result + aux;
        aux = this.cssClassName == null ? 0 : this.cssClassName.hashCode();
        result = 37 * result + aux;
        return result;
    }
}
