package com.conx.logistics.kernel.ui.components.domain.layout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.ComponentAlignment;
import com.conx.logistics.kernel.ui.components.domain.ComponentExpandRatio;

@Entity
public abstract class AbstractConXOrderedLayout extends AbstractConXLayout {
	
    /**
     * Custom layout slots containing the components.
     */
	@OneToMany
    protected List<AbstractConXComponent> components = new ArrayList<AbstractConXComponent>();
    
    /**
     * Mapping from components to alignments (horizontal + vertical).
     */
    private Set<ComponentAlignment> componentToAlignment = new HashSet<ComponentAlignment>();

    /**
     * Mapping from components to expandRatio.
     */    
    private Set<ComponentExpandRatio> componentToExpandRatio = new HashSet<ComponentExpandRatio>();

    /**
     * Is spacing between contained components enabled. Defaults to false.
     */
    private boolean spacing = false;

    public AbstractConXOrderedLayout() {
        super("abstractLayout");
    }

	public AbstractConXOrderedLayout(String typeId) {
		super(typeId);
	}
}
