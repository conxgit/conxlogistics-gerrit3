package com.conx.logistics.kernel.ui.editors.builder.vaadin;

import com.conx.logistics.kernel.ui.components.domain.form.ConXCollapseableSectionForm;
import com.conx.logistics.kernel.ui.components.domain.form.ConXForm;
import com.conx.logistics.kernel.ui.components.domain.form.ConXSimpleForm;
import com.conx.logistics.kernel.ui.editors.builder.IFormFactory;
import com.conx.logistics.kernel.ui.forms.vaadin.IVaadinForm;
import com.conx.logistics.kernel.ui.forms.vaadin.impl.VaadinCollapsibleSectionForm;
import com.conx.logistics.kernel.ui.forms.vaadin.impl.VaadinSimpleForm;

public class VaadinFormFactoryImpl implements IFormFactory {

	@Override
	public IVaadinForm create(ConXForm arg0) {
		if (arg0 instanceof ConXCollapseableSectionForm) {
			VaadinCollapsibleSectionForm form = new VaadinCollapsibleSectionForm((ConXCollapseableSectionForm) arg0);
			form.setSizeFull();
			return form;
		} else if (arg0 instanceof ConXSimpleForm) {
			VaadinSimpleForm form = new VaadinSimpleForm((ConXSimpleForm) arg0);
			form.setSizeFull();
			return form;
		}
		return null;
	}

}
