package com.conx.logistics.kernel.metamodel.dao.services.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.metamodel.domain.AbstractAttribute;
import com.conx.logistics.kernel.metamodel.domain.BasicAttribute;
import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.kernel.metamodel.domain.EntityTypeAttribute;
import com.conx.logistics.kernel.metamodel.domain.ListAttribute;
import com.conx.logistics.kernel.metamodel.domain.MapAttribute;
import com.conx.logistics.kernel.metamodel.domain.SetAttribute;
import com.conx.logistics.mdm.domain.metadata.DefaultEntityMetadata;


/**
 * Implementation of {@link EntityType} that uses JPA for persistence.<p />
 * <p/>
 * This class is marked as {@link Transactional}. The Spring configuration for this module, enables AspectJ weaving for
 * adding transaction demarcation to classes annotated with <code>@Transactional</code>.
 */
@Transactional
@Repository
public class EntityTypeDAOImpl implements IEntityTypeDAOService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	
	private transient Map<String,EntityType> cache = new HashMap<String, EntityType>(); 
	
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
    @PersistenceContext
    private EntityManager em;	
    

	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	public EntityType get(long id) {
		return em.getReference(EntityType.class, id);
	}    

	@Override
	public List<EntityType> getAll() {
		return em.createQuery("select o from com.conx.logistics.kernel.metamodel.domain.metadata.EntityType o record by o.id",EntityType.class).getResultList();
	}
	
	@Override
	public List<AbstractAttribute> getAllAttributesByEntityType(EntityType parentEntityType) {
		TypedQuery<AbstractAttribute> q = em.createQuery("select o from com.conx.logistics.kernel.metamodel.domain.AbstractAttribute o WHERE o.parentEntityType = :parentEntityType",AbstractAttribute.class);
		q.setParameter("parentEntityType",parentEntityType);
		return q.getResultList();
	}		
	
	@Override
	public List<com.conx.logistics.kernel.metamodel.domain.SingularAttribute> getAllSingularAttributesByEntityType(EntityType parentEntityType) {
		TypedQuery<com.conx.logistics.kernel.metamodel.domain.SingularAttribute> q = em.createQuery("select o from com.conx.logistics.kernel.metamodel.domain.SingularAttribute o WHERE o.parentEntityType = :parentEntityType",com.conx.logistics.kernel.metamodel.domain.SingularAttribute.class);
		q.setParameter("parentEntityType",parentEntityType);
		return q.getResultList();
	}	
	
	@Override
	public List<com.conx.logistics.kernel.metamodel.domain.PluralAttribute> getAllPluralAttributesByEntityType(EntityType parentEntityType) {
		TypedQuery<com.conx.logistics.kernel.metamodel.domain.PluralAttribute> q = em.createQuery("select o from com.conx.logistics.kernel.metamodel.domain.PluralAttribute o WHERE o.parentEntityType = :parentEntityType",com.conx.logistics.kernel.metamodel.domain.PluralAttribute.class);
		q.setParameter("parentEntityType",parentEntityType);
		return q.getResultList();
	}	

	@Override
	public List<BasicAttribute> getAllBasicAttributesByEntityType(EntityType parentEntityType) {
		TypedQuery<BasicAttribute> q = em.createQuery("select o from com.conx.logistics.kernel.metamodel.domain.BasicAttribute o WHERE o.parentEntityType = :parentEntityType",BasicAttribute.class);
		q.setParameter("parentEntityType",parentEntityType);
		
		return q.getResultList();
	}	
	
	public Long countByClass(Class entityClass) throws Exception {
		Long res = null;
		if ("Organization".equals(entityClass.getSimpleName()))
		{
			logger.error("getByClass("+entityClass.getName()+")");
		}
		TypedQuery<EntityType> typedQuery = null;
		try
		{
			logger.error("getByClass("+entityClass.getName()+")");
/*			
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<EntityType> query = builder.createQuery(EntityType.class);
			Root<EntityType> rootEntity = query.from(EntityType.class);
			ParameterExpression<String> p = builder.parameter(String.class);
			query.select(rootEntity).where(builder.equal(rootEntity.get("entityJavaSimpleType"), p));

			typedQuery = em.createQuery(query);
			typedQuery.setParameter(p, entityClass.getSimpleName());
			
			return typedQuery.getSingleResult();*/
			Query q = em.createQuery("select COUNT(o) from com.conx.logistics.kernel.metamodel.domain.EntityType o WHERE o.entityJavaSimpleType = :entityJavaSimpleType",Long.class);
			q.setParameter("entityJavaSimpleType", entityClass.getSimpleName());
			res = (Long)q.getSingleResult();
		}
		catch(NoResultException e){}
		catch(NonUniqueResultException e)
		{
			List<EntityType> rl = typedQuery.getResultList();
			rl.toString();
		}
		catch(Exception e)
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
			throw e;
		}
		catch(Error e)
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
			throw e;
		}		
		
		return res;
	}	
	
	@Override
	public EntityType getByClass(Class entityClass) throws Exception {
		EntityType record = null;
		if ("Organization".equals(entityClass.getSimpleName()))
		{
			logger.error("getByClass("+entityClass.getName()+")");
		}
		TypedQuery<EntityType> typedQuery = null;
		try
		{
			logger.error("getByClass("+entityClass.getName()+")");
/*			
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<EntityType> query = builder.createQuery(EntityType.class);
			Root<EntityType> rootEntity = query.from(EntityType.class);
			ParameterExpression<String> p = builder.parameter(String.class);
			query.select(rootEntity).where(builder.equal(rootEntity.get("entityJavaSimpleType"), p));

			typedQuery = em.createQuery(query);
			typedQuery.setParameter(p, entityClass.getSimpleName());
			
			return typedQuery.getSingleResult();*/
			typedQuery = em.createQuery("select DISTINCT  o from com.conx.logistics.kernel.metamodel.domain.EntityType o WHERE o.entityJavaSimpleType = :entityJavaSimpleType",EntityType.class);
			typedQuery.setParameter("entityJavaSimpleType", entityClass.getSimpleName());
			record = typedQuery.getSingleResult();
		}
		catch(NoResultException e){}
		catch(NonUniqueResultException e)
		{
			record = typedQuery.getResultList().get(0);
		}
		catch(Exception e)
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
			throw e;
		}
		catch(Error e)
		{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
			throw e;
		}		
		
		return record;
	}	

	@Override
	public EntityType add(EntityType record) {
		String simpleJavaType = record.getEntityJavaSimpleType();
		em.persist(record);
		//em.flush();
		//em.refresh(record);
		return record;
	}
	
	public AbstractAttribute add(AbstractAttribute record) {
		em.persist(record);
		em.flush();
		em.refresh(record);
		return record;
	}

	@Override
	public void delete(EntityType record) {
		em.remove(record);
	}

	@Override
	public EntityType update(EntityType record) {
		return em.merge(record);
	}


	@Override
	public EntityType provide(IdentifiableType jpaEntityType) throws Exception {
		String entity = jpaEntityType.getJavaType().getSimpleName();
		System.out.println("providing type for ("+entity+")");
		
		if ("Organization".equals(entity))
		{
			logger.error("getByClass("+entity+")");
		}
		
    	EntityType targetEntityType = null;
    	boolean isMappedSuperClass = false;
    	
    	long count = countByClass(jpaEntityType.getJavaType());
    	if (count == 0)
    	{
    		javax.persistence.metamodel.ManagedType mt = null;
    		
			Metamodel mm = em.getMetamodel();
	    	try {
				mt = mm.managedType(jpaEntityType.getJavaType());
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String stacktrace = sw.toString();
				logger.error(stacktrace);
			}

	    	
	    	//Create entity
	    	EntityType st = null;
	    	if (mt != null)
	    	{
		    	if (mt instanceof javax.persistence.metamodel.EntityType)
		    	{
		    		targetEntityType = new EntityType(((javax.persistence.metamodel.EntityType)mt).getName(),
			    			       mt.getJavaType(),
			    			       st,
			    			       null,//SingularAttribute id, 
			    			       null,//SingularAttribute version,
			    			       mt.getJavaType().getName());
		    		targetEntityType = add(targetEntityType);
		    		
		    		if (((javax.persistence.metamodel.EntityType)mt).getSupertype() != null)
		    		{
						st = createMappedSupperClass(((javax.persistence.metamodel.EntityType)mt).getSupertype());
						System.out.println("["+entity+"] Provided for Super EntityType ("+st.getEntityJavaSimpleType()+")");
						targetEntityType.setSuperType(st);
						targetEntityType = update(targetEntityType);
		    		}		    		
		    		
		    	}
		    	else if (mt instanceof MappedSuperclassType)
		    	{
		    		targetEntityType = new EntityType(((EntityType)mt).getName(),
		 			       mt.getJavaType(),
		 			       null,
		 			       null,//SingularAttribute id, 
		 			       null,//SingularAttribute version,
		 			       mt.getJavaType().getName());
		    		targetEntityType = add(targetEntityType);
		    		if (((MappedSuperclassType) mt).getSupertype() != null)
		    		{
		    			st = createMappedSupperClass((((MappedSuperclassType) mt).getSupertype()));
		    			System.out.println("["+entity+"] Provided for Super EntityType ("+st.getEntityJavaSimpleType()+")");
						targetEntityType.setSuperType(st);
						targetEntityType = update(targetEntityType);
		    		}		    		
		    	}
		    	
		    	targetEntityType = updateEntityTypeAttributes(targetEntityType,st,(IdentifiableType)mt);  		    	
	    	}
	    	else
	    	{
	    		//targetEntityType = getByClass(jpaEntityType.getJavaType());
	    		//if (targetEntityType == null)
	    		//{
		    		if (jpaEntityType.getSupertype() != null)
		    			st = createMappedSupperClass(jpaEntityType.getSupertype());      		
		    		targetEntityType = new EntityType(jpaEntityType.getJavaType().getName(),
		    			   jpaEntityType.getJavaType(),
		 			       st,
		 			       null,//SingularAttribute id, 
		 			       null,//SingularAttribute version,
		 			      jpaEntityType.getJavaType().getName());
			    	
			    	//Save 
			    	targetEntityType = add(targetEntityType);
			    	
			    	targetEntityType = updateEntityTypeAttributes(targetEntityType,st,jpaEntityType);
	    		//}
	    	}
    	}
    	else
    	{
    		targetEntityType = getByClass(jpaEntityType.getJavaType());
    	}
    	
		return targetEntityType;
	}


	private EntityType updateEntityTypeAttributes(
			EntityType targetEntityType, EntityType superTargetEntityType, IdentifiableType jpaEntityType) throws Exception {
		//Process attributes
		String entity = targetEntityType.getEntityJavaSimpleType();
		if ("Commodity".equals(entity))
		{
			System.out.println("Stop!");
		}

		Set<SingularAttribute> sattrs = jpaEntityType.getDeclaredSingularAttributes();
		
		Set<PluralAttribute> pattrs = jpaEntityType.getDeclaredPluralAttributes();
		
		PersistentAttributeType at;
		EntityType et;
		com.conx.logistics.kernel.metamodel.domain.AbstractAttribute attr;
		EntityType attrEntityType;
		for (SingularAttribute sattr : sattrs)
		{
			attr = null;
			at = sattr.getPersistentAttributeType();
			if (at == PersistentAttributeType.BASIC)
			{
				attr = new com.conx.logistics.kernel.metamodel.domain.BasicAttribute(sattr.isId(),sattr.getName(), sattr.getJavaType(),targetEntityType);
				attr.setParentEntityType(targetEntityType);
				attr = add(attr);
				System.out.println("["+entity+"] Adding BASIC attr ("+attr.getName()+")");
				targetEntityType.getDeclaredAttributes().add(new EntityTypeAttribute(targetEntityType,(BasicAttribute)attr));
			}
			else if ((at == PersistentAttributeType.ONE_TO_ONE) || (at == PersistentAttributeType.MANY_TO_ONE))
			{
				attrEntityType = provide((IdentifiableType)sattr.getType());
				attr = new com.conx.logistics.kernel.metamodel.domain.SingularAttribute(sattr.getName(), sattr.getJavaType(), sattr.isId(), sattr.isVersion(), sattr.isOptional(),at,attrEntityType);
				attr.setParentEntityType(targetEntityType);
				attr = add(attr);
				System.out.println("["+entity+"] Adding ONE_TO_ONE/MANY_TO_ONE attr ("+attr.getName()+")");
				targetEntityType.getDeclaredAttributes().add(new EntityTypeAttribute(targetEntityType,(com.conx.logistics.kernel.metamodel.domain.SingularAttribute)attr));				
			}
			
		}
		
		com.conx.logistics.kernel.metamodel.domain.PluralAttribute pattr_;
		for (PluralAttribute pattr : pattrs)
		{
			pattr_ = null;
			at = pattr.getPersistentAttributeType();
			if (at == PersistentAttributeType.ONE_TO_MANY || at == PersistentAttributeType.MANY_TO_MANY)
			{
				et = provide((IdentifiableType)pattr.getElementType());
				if (pattr.getCollectionType() == CollectionType.LIST)
				{
					pattr_ = new ListAttribute(pattr.getName(), et, targetEntityType,at);
					pattr_ = (com.conx.logistics.kernel.metamodel.domain.PluralAttribute)add(pattr_);
					System.out.println("["+entity+"] Adding LIST attr ("+pattr_.getName()+")");
					targetEntityType.getDeclaredAttributes().add(new EntityTypeAttribute(targetEntityType,pattr_));	
				}
				else if (pattr.getCollectionType() == CollectionType.MAP)
				{
					pattr_ = new MapAttribute(pattr.getName(), et, targetEntityType,at);
					pattr_ = (com.conx.logistics.kernel.metamodel.domain.PluralAttribute)add(pattr_);
					System.out.println("["+entity+"] Adding MAP attr ("+pattr_.getName()+")");
					targetEntityType.getDeclaredAttributes().add(new EntityTypeAttribute(targetEntityType,pattr_));
				}				
				else if (pattr.getCollectionType() == CollectionType.SET)
				{
					pattr_ = new SetAttribute(pattr.getName(), et, targetEntityType,at);
					pattr_ = (com.conx.logistics.kernel.metamodel.domain.PluralAttribute)add(pattr_);
					System.out.println("["+entity+"] Adding SET attr ("+pattr_.getName()+")");
					targetEntityType.getDeclaredAttributes().add(new EntityTypeAttribute(targetEntityType,pattr_));
				}
			}
		}

		if (superTargetEntityType != null)
		{
			 Set<EntityTypeAttribute> ssAttrs = superTargetEntityType.getDeclaredAttributes();
			 for (EntityTypeAttribute ssAttr : ssAttrs)
			 {
				 System.out.println("["+entity+"] Adding SuperClass attr ("+ssAttr.getAttribute().getName()+") for EntityType("+targetEntityType.getJpaEntityName()+")");
				 targetEntityType.getDeclaredAttributes().add(new EntityTypeAttribute(targetEntityType,ssAttr.getAttribute()));
			 }
		}		
		
		targetEntityType = add(targetEntityType);
		
		return targetEntityType;
	}

	@Override
	public EntityType createMappedSupperClass(IdentifiableType supertype) throws Exception {
		EntityType record = null;
		System.out.println("providing SuperMappedClass["+supertype.getJavaType().getSimpleName()+"] ...");

		record = getByClass(supertype.getJavaType());
		if (record == null)
		{
			//SuperType
			IdentifiableType sst = supertype.getSupertype();
			EntityType sst_ = null;
			if (sst != null)
			{
				sst_ = provide(sst);
    			System.out.println("["+supertype.getJavaType().getSimpleName()+"] Provided for Super EntityType ("+sst_.getEntityJavaSimpleType()+")");
			}
			
			record = new EntityType(supertype.getJavaType().getName(),
						supertype.getJavaType(),
						sst_,
				       null,//SingularAttribute id, 
				       null,//SingularAttribute version,
				       supertype.getJavaType().getName());
			
			//Save 
			record = add(record);
			
			record = updateEntityTypeAttributes(record,sst_,supertype);
		}
		return record;
	}


	@Override
	public AbstractAttribute getAttribute(Long entityTypeId, String name) {
		AbstractAttribute record = null;
		
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<AbstractAttribute> query = builder.createQuery(AbstractAttribute.class);
			Root<AbstractAttribute> rootEntity = query.from(AbstractAttribute.class);
			ParameterExpression<String> p = builder.parameter(String.class);
			query.select(rootEntity).where(builder.equal(rootEntity.get("name"), p));

			TypedQuery<AbstractAttribute> typedQuery = em.createQuery(query);
			typedQuery.setParameter(p, name);
			
			return typedQuery.getSingleResult();
		}
		catch(NoResultException e){}	
		
		return record;
	}	
}
