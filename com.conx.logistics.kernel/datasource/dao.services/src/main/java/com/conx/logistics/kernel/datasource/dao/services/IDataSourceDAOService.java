package com.conx.logistics.kernel.datasource.dao.services;

import java.util.List;

import javax.persistence.metamodel.IdentifiableType;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.metamodel.domain.EntityType;



public interface IDataSourceDAOService {
	public DataSource get(long id);
	
	public List<DataSource> getAll();
	
	public DataSourceField getField(Long DataSourceId, String name);	
	
	public DataSource addField(Long DataSourceId, DataSourceField field);
	
	public DataSource addFields(Long DataSourceId, List<DataSourceField> fields);
	
	public DataSource deleteField(Long DataSourceId, DataSourceField field);
	
	public DataSource deleteFields(Long DataSourceId, List<DataSourceField> fields);		

	public DataSource add(DataSource record);
	
	public DataSource provide(EntityType entityType) throws ClassNotFoundException;

	public void delete(DataSource record);

	public DataSource update(DataSource record);

	public List<DataSourceField> getFields(DataSource parentDataSource);
}
