package com.conx.logistics.kernel.ui.components.dao.services;

import java.util.List;

import com.conx.logistics.kernel.ui.components.domain.BaseComponent;

public interface IComponentDAOService {
	public BaseComponent get(long id);
	
	public List<BaseComponent> getAll();	

	public BaseComponent add(BaseComponent record);
	
	public void delete(BaseComponent record);

	public BaseComponent update(BaseComponent record);
}
