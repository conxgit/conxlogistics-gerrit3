package com.conx.logistics.kernel.metamodel.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sysmmentitytypeattribute")
public class EntityTypeAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;

	
	@ManyToOne
	private EntityType entityType;	
	
	@ManyToOne(cascade=CascadeType.ALL)
	private AbstractAttribute attribute;		

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType elementType) {
		this.entityType = elementType;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(AbstractAttribute attribute) {
		this.attribute = attribute;
	}

	public EntityTypeAttribute() {
	}

	public EntityTypeAttribute(EntityType entityType,
			AbstractAttribute attribute) {
		super();
		this.entityType = entityType;
		this.attribute = attribute;
	}	
}
