package org.twuni.common.validation;

import java.util.regex.Pattern;

public class PatternValidator implements Validator<String> {

	private final String regex;

	public PatternValidator( String regex ) {
		this.regex = regex;
	}

	@Override
	public void validate( String input ) {
		if( !Pattern.matches( regex, input ) ) {
			throw new ValidationException();
		}
	}

}
