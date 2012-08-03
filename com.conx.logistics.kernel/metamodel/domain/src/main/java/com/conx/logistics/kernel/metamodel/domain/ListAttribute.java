package com.conx.logistics.kernel.metamodel.domain;

import javax.persistence.Entity;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.PluralAttribute.CollectionType;

@Entity
public class ListAttribute extends PluralAttribute {
	
	public ListAttribute(){
	}

	public ListAttribute(String name, EntityType elementType, EntityType parentEntityType, PersistentAttributeType at) {
		super(name, elementType,parentEntityType,at);
	}

	/**
	 * {@inheritDoc}
	 */
	public CollectionType getCollectionType() {
		return CollectionType.LIST;
	}
}
