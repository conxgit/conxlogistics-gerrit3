package com.conx.logistics.kernel.criteria.domain.path;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.PluralAttribute;

import com.conx.logistics.kernel.criteria.domain.CriteriaBuilderImpl;
import com.conx.logistics.kernel.criteria.domain.PathSource;
import com.conx.logistics.kernel.criteria.domain.collection.CollectionPersister;


/**
 * Models a path for a {@link PluralAttribute} generally obtained from a
 * {@link javax.persistence.criteria.Path#get} call
 *
 */
@Entity
public class PluralAttributePath<X> extends AbstractPathImpl<X> implements Serializable {
	
	@ManyToOne
	private PluralAttribute<?,X,?> attribute;
	
	@Transient
	private final CollectionPersister persister;

	public PluralAttributePath(
			CriteriaBuilderImpl criteriaBuilder,
			PathSource source,
			PluralAttribute<?,X,?> attribute) {
		super( criteriaBuilder, attribute.getJavaType(), source );
		this.attribute = attribute;
		this.persister = resolvePersister( criteriaBuilder, attribute );
	}

	private static CollectionPersister resolvePersister(CriteriaBuilderImpl criteriaBuilder, PluralAttribute attribute) {
/*		SessionFactoryImplementor sfi = (SessionFactoryImplementor)
				criteriaBuilder.getEntityManagerFactory().getSessionFactory();
		return sfi.getCollectionPersister( resolveRole( attribute ) );*/
		return null;
	}

	private static String resolveRole(PluralAttribute attribute) {
		return attribute.getDeclaringType().getJavaType().getName() +
				'.' + attribute.getName();
	}

	public PluralAttribute<?,X,?> getAttribute() {
		return attribute;
	}

	@SuppressWarnings({ "UnusedDeclaration" })
	public CollectionPersister getPersister() {
		return persister;
	}

	@Override
	protected boolean canBeDereferenced() {
		// cannot be dereferenced
		return false;
	}

	@Override
	protected Attribute locateAttributeInternal(String attributeName) {
		throw new IllegalArgumentException( "Plural attribute paths cannot be further dereferenced" );
	}

	public Bindable<X> getModel() {
		// the issue here is the parameterized type; X is the collection
		// type (Map, Set, etc) while the "bindable" for a collection is the
		// elements.
		//
		// TODO : throw exception instead?
		return null;
	}

	@Override
	public <T extends X> PluralAttributePath<T> treatAs(Class<T> treatAsType) {
		throw new UnsupportedOperationException(
				"Plural attribute path [" + getPathSource().getPathIdentifier() + '.'
						+ attribute.getName() + "] cannot be dereferenced"
		);
	}
}
