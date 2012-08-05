package com.conx.logistics.kernel.ui.components.domain.form;

import java.util.Map;

import com.conx.logistics.kernel.ui.components.domain.form.FormEncodingException;

public interface Mappable {

    Map<String, Object> getDataMap();
    
    void setDataMap(Map<String, Object> dataMap) throws FormEncodingException;
}
