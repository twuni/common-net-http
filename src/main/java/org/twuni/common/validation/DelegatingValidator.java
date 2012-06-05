package org.twuni.common.validation;

public class DelegatingValidator<T> implements Validator<T> {

	private final Iterable<Validator<T>> validators;

	public DelegatingValidator( Iterable<Validator<T>> validators ) {
		this.validators = validators;
	}

	@Override
	public void validate( T input ) {
		for( Validator<T> validator : validators ) {
			validator.validate( input );
		}
	}

}
