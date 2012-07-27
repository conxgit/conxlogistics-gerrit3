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
@Table(name="uiCheckBoxRepresentation")
public class CheckBoxRepresentation extends FormItemRepresentation {

    private String formValue;
    private Boolean checked;
    private String name;
    private String id;

    public CheckBoxRepresentation() {
        super("checkBox");
    }

    public String getFormValue() {
        return formValue;
    }

    public void setFormValue(String formValue) {
        this.formValue = formValue;
    }

    public Boolean getChecked() {
        return checked == null ? Boolean.FALSE : checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("formValue", this.formValue);
        data.put("checked", this.checked);
        data.put("name", this.name);
        data.put("id", this.id);
        return data;
    }

    @Override
    public void setDataMap(Map<String, Object> data)
            throws FormEncodingException {
        super.setDataMap(data);
        this.formValue = (String) data.get("formValue");
        Object objChecked = (Object) data.get("checked");
        if (objChecked instanceof Boolean)
            this.checked = (Boolean) objChecked;
        else if (objChecked instanceof String)
            this.checked = Boolean.valueOf(objChecked.toString());
        this.name = (String) data.get("name");
        this.id = (String) data.get("id");
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof CheckBoxRepresentation))
            return false;
        CheckBoxRepresentation other = (CheckBoxRepresentation) obj;
        boolean equals = (this.formValue == null && other.formValue == null)
                || (this.formValue != null && this.formValue
                        .equals(other.formValue));
        if (!equals)
            return equals;
        equals = (this.checked == null && other.checked == null)
                || (this.checked != null && this.checked.equals(other.checked));
        if (!equals)
            return equals;
        equals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        if (!equals)
            return equals;
        equals = (this.id == null && other.id == null)
                || (this.id != null && this.id.equals(other.id));
        return equals;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.formValue == null ? 0 : this.formValue.hashCode();
        result = 37 * result + aux;
        aux = this.checked == null ? 0 : this.checked.hashCode();
        result = 37 * result + aux;
        aux = this.name == null ? 0 : this.name.hashCode();
        result = 37 * result + aux;
        aux = this.id == null ? 0 : this.id.hashCode();
        result = 37 * result + aux;
        return result;
    }
}
