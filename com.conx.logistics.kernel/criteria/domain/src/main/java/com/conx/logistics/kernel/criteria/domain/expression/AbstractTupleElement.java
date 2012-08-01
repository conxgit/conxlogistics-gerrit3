package com.conx.logistics.kernel.criteria.domain.expression;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.conx.logistics.kernel.criteria.domain.AbstractNode;
import com.conx.logistics.kernel.criteria.domain.CriteriaBuilderImpl;
import com.conx.logistics.kernel.criteria.domain.TupleElementImplementor;
import com.conx.logistics.kernel.criteria.domain.ValueHandlerFactory;


@MappedSuperclass
public abstract class AbstractTupleElement<X>
		extends AbstractNode
		implements TupleElementImplementor<X>, Serializable {
	
	@Transient
	private final Class originalJavaType;
	
	@Transient
	private Class<X> javaType;
	
	private String alias;
	private ValueHandlerFactory.ValueHandler<X> valueHandler;

	protected AbstractTupleElement(CriteriaBuilderImpl criteriaBuilder, Class<X> javaType) {
		super( criteriaBuilder );
		this.originalJavaType = javaType;
		this.javaType = javaType;
	}

	@Override
	public Class<X> getJavaType() {
		return javaType;
	}

	@SuppressWarnings({ "unchecked" })
	protected void resetJavaType(Class targetType) {
		this.javaType = targetType;
//		this.valueHandler = javaType.equals( originalJavaType )
//				? null
//				: ValueHandlerFactory.determineAppropriateHandler( javaType );
		this.valueHandler = ValueHandlerFactory.determineAppropriateHandler( javaType );
	}

	protected void forceConversion(ValueHandlerFactory.ValueHandler<X> valueHandler) {
		this.valueHandler = valueHandler;
	}

	@Override
	public ValueHandlerFactory.ValueHandler<X> getValueHandler() {
		return valueHandler;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	/**
	 * Protected access to define the alias.
	 *
	 * @param alias The alias to use.
	 */
	protected void setAlias(String alias) {
		this.alias = alias;
	}
}
