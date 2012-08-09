package com.conx.logistics.kernel.ui.components.dao.services;

import java.util.List;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;

public interface IComponentDAOService {
	public AbstractConXComponent get(long id);
	
	public List<AbstractConXComponent> getAll();	

	public AbstractConXComponent add(AbstractConXComponent record);
	
	public void delete(AbstractConXComponent record);

	public AbstractConXComponent update(AbstractConXComponent record);
}
