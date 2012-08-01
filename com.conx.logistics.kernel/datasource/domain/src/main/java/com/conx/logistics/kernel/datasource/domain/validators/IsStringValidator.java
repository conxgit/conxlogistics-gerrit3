package com.conx.logistics.kernel.datasource.domain.validators;

import javax.persistence.Entity;

/**
 * Validation will fail if the value is not a string value.
 */
@Entity
public class IsStringValidator extends Validator {

    public IsStringValidator() {
    	typeAsString = "isString";
    }

}
