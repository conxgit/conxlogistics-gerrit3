package com.conx.logistics.kernel.ui.components.domain.form;

import javax.persistence.Entity;

import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXField;

@Entity
public class ConXTextField extends AbstractConXTextField {
	  /**
     * Tells if input is used to enter sensitive information that is not echoed
     * to display. Typically passwords.
     */
    private boolean secret = false;

    /**
     * Number of visible rows in a multiline TextField. Value 0 implies a
     * single-line text-editor.
     */
    private int rows = 0;

    /**
     * Tells if word-wrapping should be used in multiline mode.
     */
    private boolean wordwrap = true;

	public ConXTextField() {
		super("textField");
	}
}
