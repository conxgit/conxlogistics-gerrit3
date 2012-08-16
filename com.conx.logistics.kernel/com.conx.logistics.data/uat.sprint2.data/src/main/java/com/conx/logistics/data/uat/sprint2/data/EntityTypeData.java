package com.conx.logistics.data.uat.sprint2.data;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Metamodel;

import com.conx.logistics.kernel.metamodel.dao.services.IEntityTypeDAOService;
import com.conx.logistics.kernel.metamodel.domain.EntityType;

public class EntityTypeData {
	public final static EntityType provide(IEntityTypeDAOService entityTypeDAOService,EntityManager em, Class entityjavaClass) throws Exception
	{
		Metamodel mm = em.getMetamodel();
    	javax.persistence.metamodel.EntityType me = mm.entity(entityjavaClass);
    	
    	com.conx.logistics.kernel.metamodel.domain.EntityType et = entityTypeDAOService.provide(me);
    	return et;
	}
}
