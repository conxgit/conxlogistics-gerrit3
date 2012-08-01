package com.conx.logistics.kernel.criteria.domain;

import javax.persistence.criteria.Selection;

public interface ParameterContainer {
	/**
	 * Register any parameters contained within this query component with the given registry.
	 *
	 * @param registry The parameter registry with which to register.
	 */
	public void registerParameters(ParameterRegistry registry);

	/**
	 * Helper to deal with potential parameter container nodes.
	 */
	public static class Helper {
		public static void possibleParameter(Selection selection, ParameterRegistry registry) {
			if ( ParameterContainer.class.isInstance( selection ) ) {
				( (ParameterContainer) selection ).registerParameters( registry );
			}
		}
	}
}