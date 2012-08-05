package com.conx.logistics.kernel.ui.components.domain.tabbed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="sysuitabsettab")
public class TabSetTab {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    	
    @Version
    @Column(name = "version")
    private Integer version;
    
    @ManyToOne
    private TabSetComponent tabSet;
    
    @ManyToOne
    private TabComponent tab;

	public TabSetTab(TabSetComponent tabSet, TabComponent tab) {
		super();
		this.tabSet = tabSet;
		this.tab = tab;
	}
}
