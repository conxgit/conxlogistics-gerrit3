package com.conx.logistics.kernel.ui.components.dao.services;

import java.util.List;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;

public interface IComponentDAOService {
	public AbstractConXComponent get(long id);
	
	public AbstractConXComponent getByCode(String code);
	
	public MasterDetailComponent getMasterDetailByDataSource(DataSource ds);
	
	public List<AbstractConXComponent> getAll();	

	public AbstractConXComponent add(AbstractConXComponent record);
	
	public void delete(AbstractConXComponent record);

	public AbstractConXComponent update(AbstractConXComponent record);
}
