package com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.fieldfactory;

import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectTranslator;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.NativeSelect;

public class ConXFieldFactory extends FieldFactory {

	public ConXFieldFactory() {
		super();
	}

	@Override
	protected Field createManyToOneField(EntityContainer containerForProperty,
			Object itemId, Object propertyId, Component uiContext) {
		Class<?> type = containerForProperty.getType(propertyId);
        JPAContainer container = createJPAContainerFor(containerForProperty,
                type, false);

        AbstractSelect nativeSelect = constructReferenceSelect(
                containerForProperty, itemId, propertyId, uiContext, type);
        nativeSelect.setMultiSelect(false);
        nativeSelect.setItemCaptionPropertyId("code");
        nativeSelect.setCaption(DefaultFieldFactory
                .createCaptionByPropertyId(propertyId));
        nativeSelect.setItemCaptionMode(NativeSelect.ITEM_CAPTION_MODE_PROPERTY);
        nativeSelect.setContainerDataSource(container);
        nativeSelect.setPropertyDataSource(new SingleSelectTranslator(
                nativeSelect));
        return nativeSelect;
	}
}
