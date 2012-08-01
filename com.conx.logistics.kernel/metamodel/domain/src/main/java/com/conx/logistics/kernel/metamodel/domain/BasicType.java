package com.conx.logistics.kernel.metamodel.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.Type.PersistenceType;

@Entity
public class BasicType extends AbstractType {
	public BasicType(){
		super();
	}	

	public BasicType(String name, Class javaType) {
		super(name, javaType, javaType.getName(), javaType.getSimpleName(),PersistenceType.BASIC);
	}

}
