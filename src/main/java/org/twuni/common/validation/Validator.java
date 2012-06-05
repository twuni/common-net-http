package org.twuni.common.validation;

public interface Validator<T> {

	/**
	 * @throws ValidationException
	 *             if the given input fails to validate.
	 */
	public void validate( T input );

}
