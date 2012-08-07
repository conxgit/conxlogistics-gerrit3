package com.conx.logistics.kernel.ui.components.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.mdm.domain.MultitenantBaseEntity;

@Entity
public class MarginInfo extends AbstractConXComponent implements Serializable {

    private boolean top = true;
    private boolean right = true;
    private boolean bottom = true;
    private boolean left = true;
    
    
    
	public MarginInfo(boolean top, boolean right,
			boolean bottom, boolean left) {
		this();
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}



	public MarginInfo() {
		super("margiInfo");
	}
}
