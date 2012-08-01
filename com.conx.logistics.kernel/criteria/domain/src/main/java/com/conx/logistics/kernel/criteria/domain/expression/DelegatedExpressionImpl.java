package com.conx.logistics.kernel.criteria.domain.expression;


import javax.persistence.criteria.Selection;

import com.conx.logistics.kernel.criteria.domain.ParameterRegistry;
import com.conx.logistics.kernel.criteria.domain.ValueHandlerFactory;

import java.util.List;

/**
 * Implementation of {@link javax.persistence.criteria.Expression} wraps another Expression and delegates most of its
 * functionality to that wrapped Expression
 */
public abstract class DelegatedExpressionImpl<T> extends ExpressionImpl<T> {
	private final ExpressionImpl<T> wrapped;

	public DelegatedExpressionImpl(ExpressionImpl<T> wrapped) {
		super( wrapped.criteriaBuilder(), wrapped.getJavaType() );
		this.wrapped = wrapped;
	}

	public ExpressionImpl<T> getWrapped() {
		return wrapped;
	}


	// delegations ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@Override
	public void registerParameters(ParameterRegistry registry) {
		wrapped.registerParameters( registry );
	}

	@Override
	public Selection<T> alias(String alias) {
		wrapped.alias( alias );
		return this;
	}

	@Override
	public boolean isCompoundSelection() {
		return wrapped.isCompoundSelection();
	}

	@Override
	public List<ValueHandlerFactory.ValueHandler> getValueHandlers() {
		return wrapped.getValueHandlers();
	}

	@Override
	public List<Selection<?>> getCompoundSelectionItems() {
		return wrapped.getCompoundSelectionItems();
	}

	@Override
	public Class<T> getJavaType() {
		return wrapped.getJavaType();
	}

	@Override
	protected void resetJavaType(Class targetType) {
		wrapped.resetJavaType( targetType );
	}

	@Override
	protected void forceConversion(ValueHandlerFactory.ValueHandler<T> tValueHandler) {
		wrapped.forceConversion( tValueHandler );
	}

	@Override
	public ValueHandlerFactory.ValueHandler<T> getValueHandler() {
		return wrapped.getValueHandler();
	}

	@Override
	public String getAlias() {
		return wrapped.getAlias();
	}

	@Override
	protected void setAlias(String alias) {
		wrapped.setAlias( alias );
	}
}
