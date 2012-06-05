package org.twuni.common.net.http.request.validation;

import java.util.ArrayList;
import java.util.List;

import org.twuni.common.validation.DelegatingValidator;
import org.twuni.common.validation.OccurrenceValidator;
import org.twuni.common.validation.PatternValidator;
import org.twuni.common.validation.ToleratingValidator;
import org.twuni.common.validation.Validator;

public class HeaderValidator implements Validator<String> {

	private final Validator<String> validator;

	public HeaderValidator( int maximumNumberOfHeaderLines, int maximumNumberOfInvalidHeaderLines, int maximumContentLengthHeaderValue ) {

		List<Validator<String>> validators = new ArrayList<Validator<String>>();

		validators.add( new OccurrenceValidator<String>( maximumNumberOfHeaderLines ) );
		validators.add( new ToleratingValidator<String>( new PatternValidator( "^([^:]+): (.+)$" ), maximumNumberOfInvalidHeaderLines ) );
		validators.add( new ContentLengthValidator( maximumContentLengthHeaderValue ) );

		validator = new DelegatingValidator<String>( validators );

	}

	@Override
	public void validate( String input ) {
		validator.validate( input );
	}

}
