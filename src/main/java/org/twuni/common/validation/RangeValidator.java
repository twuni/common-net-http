package org.twuni.common.validation;

public class RangeValidator implements Validator<Integer> {

	private final int minimum;
	private final int maximum;

	public RangeValidator( int maximum ) {
		this( 0, maximum );
	}

	public RangeValidator( int minimum, int maximum ) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	@Override
	public void validate( Integer input ) {
		int value = input.intValue();
		if( value < minimum || value > maximum ) {
			throw new ValidationException();
		}
	}

}
