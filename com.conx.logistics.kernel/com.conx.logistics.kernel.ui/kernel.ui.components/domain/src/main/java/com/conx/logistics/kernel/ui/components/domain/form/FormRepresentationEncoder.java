package com.conx.logistics.kernel.ui.components.domain.form;

import java.util.List;
import java.util.Map;

import com.conx.logistics.kernel.ui.components.domain.form.FormItemRepresentation;
import com.conx.logistics.kernel.ui.components.domain.form.FormRepresentation;
import com.conx.logistics.kernel.ui.components.domain.form.menu.MenuItemDescription;

public interface FormRepresentationEncoder {

    String encode(FormRepresentation form) throws FormEncodingException;
    
    String encode(FormItemRepresentation item) throws FormEncodingException;

    String encodeMenuItemsMap(Map<String, List<MenuItemDescription>> items) throws FormEncodingException;
}
