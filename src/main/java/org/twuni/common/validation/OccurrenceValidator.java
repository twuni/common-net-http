package org.twuni.common.validation;

/**
 * This validator throws a ValidationException after validate() has been called a number of times
 * exceeding its tolerance.
 */
public class OccurrenceValidator<T> extends ToleratingValidator<T> {

	private static final class FailingValidator<T> implements Validator<T> {

		@Override
		public void validate( T input ) {
			throw new ValidationException();
		}

	}

	public OccurrenceValidator( int tolerance ) {
		super( new FailingValidator<T>(), tolerance );
	}

}
