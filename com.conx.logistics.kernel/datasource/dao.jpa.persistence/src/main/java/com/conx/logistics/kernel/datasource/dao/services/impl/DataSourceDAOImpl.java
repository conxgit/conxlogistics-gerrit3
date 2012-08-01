package com.conx.logistics.kernel.datasource.dao.services.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.MappedSuperclassType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.PluralAttribute.CollectionType;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type.PersistenceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.kernel.datasource.dao.services.IDataSourceDAOService;
import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.datasource.domain.DataSourceField;



/**
 * Implementation of {@link DataSource} that uses JPA for persistence.<p />
 * <p/>
 * This class is marked as {@link Transactional}. The Spring configuration for this module, enables AspectJ weaving for
 * adding transaction demarcation to classes annotated with <code>@Transactional</code>.
 */
@Transactional
@Repository
public class DataSourceDAOImpl implements IDataSourceDAOService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	
	private transient Map<String,DataSource> cache = new HashMap<String, DataSource>(); 
	
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
    @PersistenceContext
    private EntityManager em;	
    

	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	public DataSource get(long id) {
		return em.getReference(DataSource.class, id);
	}    

	@Override
	public List<DataSource> getAll() {
		return em.createQuery("select o from com.conx.logistics.kernel.datasource.domain.DataSource o record by o.id",DataSource.class).getResultList();
	}
	
	@Override
	public List<DataSourceField> getFields(DataSource parentDataSource) {
		TypedQuery<DataSourceField> q = em.createQuery("select o from com.conx.logistics.kernel.datasource.domain.DataSourceField o WHERE o.dataSource = :parentDataSource",DataSourceField.class);
		q.setParameter("parentDataSource",parentDataSource);
		return q.getResultList();
	}		


	@Override
	public DataSource add(DataSource record) {
		record = em.merge(record);
		
		return record;
	}

	@Override
	public void delete(DataSource record) {
		em.remove(record);
	}

	@Override
	public DataSource update(DataSource record) {
		return em.merge(record);
	}

	@Override
	public DataSourceField getField(Long dataSourceId, String name) {
		DataSourceField record = null;
		
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<DataSourceField> query = builder.createQuery(DataSourceField.class);
			Root<DataSourceField> rootEntity = query.from(DataSourceField.class);
			ParameterExpression<String> p = builder.parameter(String.class);
			query.select(rootEntity).where(builder.equal(rootEntity.get("name"), p));

			TypedQuery<DataSourceField> typedQuery = em.createQuery(query);
			typedQuery.setParameter(p, name);
			
			return typedQuery.getSingleResult();
		}
		catch(NoResultException e){}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		catch(Error e)
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
		}		
		
		return record;
	}


	@Override
	public DataSource addField(Long dataSourceId, DataSourceField field) {
		DataSource ds = em.getReference(DataSource.class, dataSourceId);
		ds.getFields().add(field);
		
		ds = em.merge(ds);
		
		return ds;
	}


	@Override
	public DataSource addFields(Long dataSourceId, List<DataSourceField> fields) {
		DataSource ds = em.getReference(DataSource.class, dataSourceId);
		ds.getFields().addAll(fields);
		
		ds = em.merge(ds);
		
		return ds;
	}


	@Override
	public DataSource deleteField(Long dataSourceId, DataSourceField field) {
		DataSource ds = em.getReference(DataSource.class, dataSourceId);
		ds.getFields().remove(field);
		
		ds = em.merge(ds);
		
		return ds;
	}


	@Override
	public DataSource deleteFields(Long dataSourceId,
			List<DataSourceField> fields) {
		DataSource ds = em.getReference(DataSource.class, dataSourceId);
		ds.getFields().removeAll(fields);
		
		ds = em.merge(ds);
		
		return ds;
	}	
}
