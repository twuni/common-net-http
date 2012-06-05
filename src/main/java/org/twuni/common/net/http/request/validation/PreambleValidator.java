package org.twuni.common.net.http.request.validation;

import org.twuni.common.validation.PatternValidator;
import org.twuni.common.validation.ToleratingValidator;
import org.twuni.common.validation.Validator;

public class PreambleValidator implements Validator<String> {

	private final Validator<String> validator;

	public PreambleValidator( int maximumNumberOfInvalidPreambleLines ) {
		validator = new ToleratingValidator<String>( new PatternValidator( "[A-Z]+ [^ ]+ HTTP/[0-9]+\\.[0-9]+" ), maximumNumberOfInvalidPreambleLines );
	}

	@Override
	public void validate( String input ) {
		validator.validate( input );
	}

}
