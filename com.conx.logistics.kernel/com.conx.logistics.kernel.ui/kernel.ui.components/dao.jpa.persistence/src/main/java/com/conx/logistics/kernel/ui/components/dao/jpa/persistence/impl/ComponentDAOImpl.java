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

import com.conx.logistics.kernel.datasource.domain.DataSource;
import com.conx.logistics.kernel.metamodel.dao.services.IBasicTypeDAOService;
import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.ui.components.dao.services.IComponentDAOService;
import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;



/**
 * Implementation of {@link AbstractConXComponent} that uses JPA for persistence.<p />
 * <p/>
 * This class is marked as {@link Transactional}. The Spring configuration for this module, enables AspectJ weaving for
 * adding transaction demarcation to classes annotated with <code>@Transactional</code>.
 */
@Transactional
@Repository
public class ComponentDAOImpl implements IComponentDAOService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	
	private transient Map<String,AbstractConXComponent> cache = new HashMap<String, AbstractConXComponent>(); 
	
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
	public AbstractConXComponent get(long id) {
		return em.getReference(AbstractConXComponent.class, id);
	}    

	@Override
	public List<AbstractConXComponent> getAll() {
		return em.createQuery("select o from com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent o record by o.id",AbstractConXComponent.class).getResultList();
	}

	@Override
	public AbstractConXComponent add(AbstractConXComponent record) {
		record = em.merge(record);
		
		return record;
	}

	@Override
	public AbstractConXComponent getByCode(String code) {
		AbstractConXComponent ds = null;
		
		try
		{
		TypedQuery<AbstractConXComponent> q = em.createQuery("select DISTINCT o from com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent o WHERE o.code = :code",AbstractConXComponent.class);
		q.setParameter("code",code);
		ds = q.getSingleResult();
		}
		catch(javax.persistence.NoResultException e)
		{}
		
		return ds;
	}



	@Override
	public void delete(AbstractConXComponent record) {
		em.remove(record);
	}

	@Override
	public AbstractConXComponent update(AbstractConXComponent record) {
		return em.merge(record);
	}


	@Override
	public MasterDetailComponent getMasterDetailByDataSource(DataSource ds) {
		MasterDetailComponent mdc = null;
		
		try
		{
		TypedQuery<MasterDetailComponent> q = em.createQuery("select DISTINCT o from com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent o WHERE o.typeId = :typeId AND o.dataSource = :dataSource",MasterDetailComponent.class);
		q.setParameter("typeId","masterdetailcomponent");
		q.setParameter("dataSource",ds);
		mdc = q.getSingleResult();
		}
		catch(javax.persistence.NoResultException e)
		{}
		
		return mdc;		
	}
}
