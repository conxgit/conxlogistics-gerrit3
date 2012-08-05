package com.conx.logistics.kernel.ui.components.domain.form;

import java.util.Map;

public interface Formatter {

    Object format(Object object);

    Map<String, Object> getDataMap();
}
