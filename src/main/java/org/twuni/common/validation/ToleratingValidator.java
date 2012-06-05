package org.twuni.common.validation;

/**
 * This validator tolerates up to a certain number of validation failures.
 */
public class ToleratingValidator<T> implements Validator<T> {

	private final Validator<T> validator;
	private final int tolerance;
	private int failures = 0;

	public ToleratingValidator( Validator<T> validator, int tolerance ) {
		this.validator = validator;
		this.tolerance = tolerance;
	}

	@Override
	public void validate( T input ) {
		try {
			validator.validate( input );
		} catch( ValidationException exception ) {
			failures++;
			if( failures >= tolerance ) {
				throw new ValidationException();
			}
		}
	}

}
