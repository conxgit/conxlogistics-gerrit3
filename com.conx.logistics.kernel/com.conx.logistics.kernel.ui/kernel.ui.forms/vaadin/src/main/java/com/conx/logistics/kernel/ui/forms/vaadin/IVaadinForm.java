package com.conx.logistics.kernel.ui.forms.vaadin;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;

public interface IVaadinForm extends Component {
	public void setItemDataSource(Item item);
}
