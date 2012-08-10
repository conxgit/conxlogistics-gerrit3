package com.conx.logistics.app.whse.dao.services.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.app.whse.dao.services.IWarehouseDAOService;
import com.conx.logistics.app.whse.domain.constants.WarehouseCustomCONSTANTS;
import com.conx.logistics.app.whse.domain.warehouse.Warehouse;
import com.conx.logistics.common.utils.Validator;

@Transactional
@Repository
public class WarehouseDAOImpl implements IWarehouseDAOService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
	@PersistenceContext
    private EntityManager em;	
    
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Warehouse get(long id) {
		return em.getReference(Warehouse.class, id);
	}    

	@Override
	public List<Warehouse> getAll() {
		return em.createQuery("select o from com.conx.logistics.app.whse.domain.warehouse.Warehouse o record order by o.id",Warehouse.class).getResultList();
	}
	
	@Override
	public Warehouse getByCode(String code) {
		Warehouse org = null;
		
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Warehouse> query = builder.createQuery(Warehouse.class);
			Root<Warehouse> rootEntity = query.from(Warehouse.class);
			ParameterExpression<String> p = builder.parameter(String.class);
			query.select(rootEntity).where(builder.equal(rootEntity.get("code"), p));

			TypedQuery<Warehouse> typedQuery = em.createQuery(query);
			typedQuery.setParameter(p, code);
			
			org = typedQuery.getSingleResult();
			/*
			TypedQuery<Warehouse> q = em.createQuery("select o from com.conx.logistics.app.whse.domain.Warehouse.Warehouse o WHERE o.code = :code",Warehouse.class);
			q.setParameter("code", code);
						
			org = q.getSingleResult();
			*/
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
		
		return org;
	}	

	@Override
	public Warehouse add(Warehouse record) {
		record = em.merge(record);
		
		return record;
	}

	@Override
	public void delete(Warehouse record) {
		em.remove(record);
	}

	@Override
	public Warehouse update(Warehouse record) {
		return em.merge(record);
	}


	@Override
	public Warehouse provide(Warehouse record) {
		Warehouse existingRecord = getByCode(record.getCode());
		if (Validator.isNull(existingRecord))
		{		
			record = update(record);
			try {
				//em.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return record;
	}
	
	@Override
	public Warehouse provide(String code, String name) {
		Warehouse res = null;
		if ((res = getByCode(code)) == null)
		{
			Warehouse unit = new Warehouse();

			unit.setCode(code);
			unit.setName(name);

			res = add(unit);
		}
		return res;
	}

	@Override
	public void provideDefaults() {
		provide(WarehouseCustomCONSTANTS.DEFAULT_WAREHOUSE_CODE, WarehouseCustomCONSTANTS.DEFAULT_WAREHOUSE_NAME);
	}	
}
