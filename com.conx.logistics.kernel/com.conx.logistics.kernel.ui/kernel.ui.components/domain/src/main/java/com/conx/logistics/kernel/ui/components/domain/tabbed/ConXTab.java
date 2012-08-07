package com.conx.logistics.kernel.ui.components.domain.tabbed;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class ConXTab extends AbstractConXComponent implements IConXTab {

    private String caption = "";
    private String iconPath = null;
    private boolean enabled = true;
    private boolean visible = true;
    private boolean closable = false;
    private String description = null;
    private String styleName;
    
    @OneToOne
    private AbstractConXComponent component;
    
    public ConXTab() {
        super("tab");
    }
    
    /**
     * Returns the tab caption. Can never be null.
     */
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
