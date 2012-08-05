package com.conx.logistics.kernel.ui.components.dao.jpa.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.kernel.metamodel.dao.services.IBasicTypeDAOService;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.ui.components.dao.services.IComponentDAOService;
import com.conx.logistics.kernel.ui.components.domain.BaseComponent;



/**
 * Implementation of {@link BaseComponent} that uses JPA for persistence.<p />
 * <p/>
 * This class is marked as {@link Transactional}. The Spring configuration for this module, enables AspectJ weaving for
 * adding transaction demarcation to classes annotated with <code>@Transactional</code>.
 */
@Transactional
@Repository
public class ComponentDAOImpl implements IComponentDAOService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	
	private transient Map<String,BaseComponent> cache = new HashMap<String, BaseComponent>(); 
	
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private IEntityTypeDAOService entityTypeDao;
    
    @Autowired
    private IBasicTypeDAOService basicTypeDao;
    

	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	public BaseComponent get(long id) {
		return em.getReference(BaseComponent.class, id);
	}    

	@Override
	public List<BaseComponent> getAll() {
		return em.createQuery("select o from com.conx.logistics.kernel.ui.components.domain.BaseComponent o record by o.id",BaseComponent.class).getResultList();
	}

	@Override
	public BaseComponent add(BaseComponent record) {
		record = em.merge(record);
		
		return record;
	}

	private BaseComponent getByCode(String code) {
		BaseComponent ds = null;
		
		try
		{
		TypedQuery<BaseComponent> q = em.createQuery("select DISTINCT o from com.conx.logistics.kernel.ui.components.domain.BaseComponent o WHERE o.code = :code",BaseComponent.class);
		q.setParameter("code",code);
		ds = q.getSingleResult();
		}
		catch(javax.persistence.NoResultException e)
		{}
		
		return ds;
	}



	@Override
	public void delete(BaseComponent record) {
		em.remove(record);
	}

	@Override
	public BaseComponent update(BaseComponent record) {
		return em.merge(record);
	}
}
