package com.conx.logistics.kernel.metamodel.dao.services;

import java.util.List;

import javax.persistence.metamodel.IdentifiableType;

import com.conx.logistics.kernel.metamodel.domain.AbstractAttribute;
import com.conx.logistics.kernel.metamodel.domain.BasicAttribute;
import com.conx.logistics.kernel.metamodel.domain.EntityType;
import com.conx.logistics.kernel.metamodel.domain.PluralAttribute;
import com.conx.logistics.kernel.metamodel.domain.SingularAttribute;


public interface IEntityTypeDAOService {
	public EntityType get(long id);
	
	public List<EntityType> getAll();
	
	public EntityType getByClass(Class entityClass) throws Exception;	
	
	public AbstractAttribute getAttribute(Long entityTypeId, String name);	

	public EntityType add(EntityType record);

	public void delete(EntityType record);

	public EntityType update(EntityType record);
	
	public EntityType provide(IdentifiableType record) throws ClassNotFoundException, Exception;

	public List<BasicAttribute> getAllBasicAttributesByEntityType(EntityType parentEntityType);

	public List<AbstractAttribute> getAllAttributesByEntityType(EntityType parentEntityType);

	public List<SingularAttribute> getAllSingularAttributesByEntityType(EntityType parentEntityType);

	public List<PluralAttribute> getAllPluralAttributesByEntityType(EntityType parentEntityType);

	public EntityType createMappedSupperClass(IdentifiableType supertype) throws Exception;
}
