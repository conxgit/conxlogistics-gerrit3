package com.conx.logistics.kernel.ui.components.domain.tabbed;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.components.domain.BaseComponent;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="sysuitabcomponent")
public class TabComponent extends BaseComponent {
    private int ordinal;
    
    @ManyToOne
    private BaseComponent tabContent;
}
