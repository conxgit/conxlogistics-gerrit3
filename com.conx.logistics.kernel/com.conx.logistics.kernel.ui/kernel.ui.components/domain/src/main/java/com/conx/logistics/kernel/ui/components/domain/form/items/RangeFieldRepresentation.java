package com.conx.logistics.kernel.ui.components.domain.form.items;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.components.domain.form.FormItemRepresentation;
import com.conx.logistics.kernel.ui.components.domain.form.FormEncodingException;



@Entity
public class RangeFieldRepresentation extends FormItemRepresentation {

    private Double defaultValue;
    private String name;
    private String id;
    private Double min;
    private Double max;
    private Double step;

    public RangeFieldRepresentation() {
        super("rangeField");
    }

    public Double getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getStep() {
        return step;
    }

    public void setStep(Double step) {
        this.step = step;
    }

    @Override
    public Map<String, Object> getDataMap() {
        Map<String, Object> data = super.getDataMap();
        data.put("defaultValue", this.defaultValue);
        data.put("name", this.name);
        data.put("id", this.id);
        data.put("max", this.max);
        data.put("min", this.min);
        data.put("step", this.step);
        return data;
    }

    @Override
    public void setDataMap(Map<String, Object> data)
            throws FormEncodingException {
        super.setDataMap(data);
        this.name = (String) data.get("name");
        this.id = (String) data.get("id");
        Object obj = data.get("max");
        if (obj != null) {
            this.max = ((Number) obj).doubleValue();
        }
        obj = data.get("min");
        if (obj != null) {
            this.min = ((Number) obj).doubleValue();
        }
        obj = data.get("step");
        if (obj != null) {
            this.step = ((Number) obj).doubleValue();
        }
        obj = data.get("defaultValue");
        if (obj != null) {
            this.defaultValue = ((Number) obj).doubleValue();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof RangeFieldRepresentation))
            return false;
        RangeFieldRepresentation other = (RangeFieldRepresentation) obj;
        boolean equals = (this.defaultValue == null && other.defaultValue == null)
                || (this.defaultValue != null && this.defaultValue
                        .equals(other.defaultValue));
        if (!equals)
            return equals;
        equals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        if (!equals)
            return equals;
        equals = (this.id == null && other.id == null)
                || (this.id != null && this.id.equals(other.id));
        if (!equals)
            return equals;
        equals = (this.max == null && other.max == null)
                || (this.max != null && this.max.equals(other.max));
        if (!equals)
            return equals;
        equals = (this.min == null && other.min == null)
                || (this.min != null && this.min.equals(other.min));
        if (!equals)
            return equals;
        equals = (this.step == null && other.step == null)
                || (this.step != null && this.step.equals(other.step));
        return equals;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        int aux = this.defaultValue == null ? 0 : this.defaultValue.hashCode();
        result = 37 * result + aux;
        aux = this.name == null ? 0 : this.name.hashCode();
        result = 37 * result + aux;
        aux = this.id == null ? 0 : this.id.hashCode();
        result = 37 * result + aux;
        aux = this.max == null ? 0 : this.max.hashCode();
        result = 37 * result + aux;
        aux = this.min == null ? 0 : this.min.hashCode();
        result = 37 * result + aux;
        aux = this.step == null ? 0 : this.step.hashCode();
        result = 37 * result + aux;
        return result;
    }

}
