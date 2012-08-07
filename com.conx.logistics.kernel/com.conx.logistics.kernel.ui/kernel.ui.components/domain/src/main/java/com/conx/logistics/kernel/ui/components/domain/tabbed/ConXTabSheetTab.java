package com.conx.logistics.kernel.ui.components.domain.tabbed;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

@Entity
public class ConXTabSheetTab extends AbstractConXComponent {

	@OneToOne
    private ConXTabSheet tabSheet;
	
	@OneToOne
	private ConXTab tab;
	
	private int ordinal;
	
    
    public ConXTabSheetTab(String typeId, ConXTabSheet tabSheet, ConXTab tab,
			int ordinal) {
		super(typeId);
		this.tabSheet = tabSheet;
		this.tab = tab;
		this.ordinal = ordinal;
	}

	public ConXTabSheetTab() {
        super("tabSheetTab");
    }

	public ConXTabSheet getTabSheet() {
		return tabSheet;
	}

	public void setTabSheet(ConXTabSheet tabSheet) {
		this.tabSheet = tabSheet;
	}

	public ConXTab getTab() {
		return tab;
	}

	public void setTab(ConXTab tab) {
		this.tab = tab;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
}
