package com.conx.logistics.kernel.ui.components.domain.tabbed;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class ConXTab extends AbstractConXComponent implements IConXTab {

    private boolean closable = false;
    private String styleName;
    
    @OneToOne
    private AbstractConXComponent component;
    
    public ConXTab() {
        super("tab");
    }

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getStyleName() {
        return styleName;
    }

	@Override
	public AbstractConXComponent getComponent() {
		return component;
	}    
}
