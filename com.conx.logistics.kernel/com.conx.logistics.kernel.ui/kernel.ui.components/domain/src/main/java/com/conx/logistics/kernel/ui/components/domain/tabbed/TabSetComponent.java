package com.conx.logistics.kernel.ui.components.domain.tabbed;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.conx.logistics.kernel.ui.components.domain.BaseComponent;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="sysuitabsetcomponent")
public class TabSetComponent extends BaseComponent {
	@OneToMany(mappedBy="tabSet")
	private Set<TabSetTab> tabs = new HashSet<TabSetTab>();
}
