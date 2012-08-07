package com.conx.logistics.app.whse.rcv.rcv.domain.types;

public enum RECEIVELINESTATUS {
	AWAITING_ARRIVAL("AWA","Awaiting Arrival",1),
	ARRIVING("ARV","Arriving",2),
	ARRIVED("AVD","Arrived",3),
	ONHOLD("OHD","On Hold",3),
	DEMAGED("DMG","Demaged",3),
	CANCELLED("CCL","Cancelled",2);
	
    private String abbr = "";
    private String description = "";
    private int ordinal;
    
    private RECEIVELINESTATUS(String abbr,String description,int ordinal)
    {
    	this.abbr = abbr;
    	this.description = description;
    	this.ordinal = ordinal;
    }

	public String getAbbr() {
		return abbr;
	}   
	
	public String getDescription() {
		return description;
	}

	public int getOrdinal() {
		return ordinal;
	}	
}
