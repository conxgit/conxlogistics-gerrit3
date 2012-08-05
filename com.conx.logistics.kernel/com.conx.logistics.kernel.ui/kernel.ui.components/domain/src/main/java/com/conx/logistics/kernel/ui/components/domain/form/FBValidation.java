package com.conx.logistics.kernel.ui.components.domain.form;

public interface FBValidation extends Mappable {

    boolean isValid(Object obj);
    
    FBValidation cloneValidation();
    
    String getValidationId();
}
