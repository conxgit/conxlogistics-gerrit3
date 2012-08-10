package com.conx.logistics.app.whse.dao.services;

import java.util.List;

import com.conx.logistics.app.whse.domain.warehouse.Warehouse;

public interface IWarehouseDAOService {
	/**
	 * Warehouse App
	 */
	//== App
	public static final String WAREHOUSE_APP_NAME = "Warehouse";
	public static final String WAREHOUSE_APP_CODE = "WHSE";
	
	//==== Modules Featureset
	public static final String WAREHOUSE_APP_RECEIVING_NAME = "Receiving";
	public static final String WAREHOUSE_APP_RECEIVING_CODE = "RCVNG";	
	
	//========= Modules :: Search Feature
	public static final String WAREHOUSE_APP_RECEIVING_ASNS_SEARCH_NAME = "ASN's";
	public static final String WAREHOUSE_APP_RECEIVING_ASNS_SEARCH_CODE = "ASN.SEARCH";
	
	public Warehouse get(long id);
	
	public List<Warehouse> getAll();
	
	public Warehouse getByCode(String code);	

	public Warehouse add(Warehouse record);

	public void delete(Warehouse record);

	public Warehouse update(Warehouse record);
	
	public Warehouse provide(Warehouse record);
	
	public Warehouse provide(String code, String name);
	
	public void provideDefaults();	
}
