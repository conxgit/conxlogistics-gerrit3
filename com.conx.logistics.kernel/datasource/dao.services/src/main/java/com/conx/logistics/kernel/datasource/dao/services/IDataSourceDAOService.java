package com.conx.logistics.kernel.datasource.dao.services;

import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.IdentifiableType;

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;
import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.kernel.metamodel.domain.EntityTypeAttribute;



public interface IDataSourceDAOService {
	public DataSource get(long id);
	
	public DataSource getByCode(String code);	
	
	public List<DataSource> getAll();
	
	public DataSource getByEntityType(EntityType entityType);
	
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

	public DataSourceField provideDataSourceField(DataSource targetDataSource,
			EntityTypeAttribute aattr) throws ClassNotFoundException;

	public DataSourceField getFieldByName(DataSource parentDataSource, String attrName);

	public DataSourceField provideDataSourceFieldByAttrName(
			DataSource targetDataSource, String aattrName)
			throws ClassNotFoundException;

	public DataSource addField(DataSource record, DataSourceField dsf);

	public DataSource addFields(DataSource record, Set<DataSourceField> dsfs);
}
