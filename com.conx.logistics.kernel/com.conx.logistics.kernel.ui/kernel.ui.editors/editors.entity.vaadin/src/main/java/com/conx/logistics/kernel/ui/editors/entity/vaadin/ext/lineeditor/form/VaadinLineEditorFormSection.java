package com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.lineeditor.form;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.form.ConXForm;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.editors.builder.IFormFactory;
import com.conx.logistics.kernel.ui.forms.vaadin.IVaadinForm;
import com.vaadin.data.Item;
import com.vaadin.ui.VerticalLayout;

public class VaadinLineEditorFormSection extends VerticalLayout {
	private static final long serialVersionUID = -99999999921L;

	private VaadinFormHeader header;
	private IVaadinForm content;
	private IFormFactory formFactory;

	public VaadinLineEditorFormSection(LineEditorComponent lineEditorSection, IFormFactory formFactory) {
		this.formFactory = formFactory;
		setSizeFull();
		
		initialize(lineEditorSection);
	}

	private void initialize(LineEditorComponent lineEditorSection) {
		AbstractConXComponent component = lineEditorSection.getContent();
		if (component instanceof ConXForm) {
			this.header = new VaadinFormHeader();
			this.header.setAction("Editing");
			this.header.setTitle(lineEditorSection.getCaption());
			this.content = (IVaadinForm) formFactory.create((ConXForm) component);

			addComponent(header);
			addComponent(content);
			setExpandRatio(content, 1.0f);
		}
	}
	
	public void setFormItemDataSource(Item item) {
		this.content.setItemDataSource(item);
	}
	
	public void setFormMode(String mode) {
		this.header.setAction(mode);
	}
}
