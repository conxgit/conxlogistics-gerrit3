package com.conx.logistics.kernel.ui.components.domain.tabbed;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class ConXTabSheet extends AbstractConXComponent {

	@OneToMany(mappedBy="tabSheet")
    protected List<ConXTabSheetTab> components = new ArrayList<ConXTabSheetTab>();
    
    public ConXTabSheet() {
        super("tabSheet");
        setWidth("100%");
    }
}
