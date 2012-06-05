package org.twuni.common.net.http.request.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.twuni.common.validation.RangeValidator;
import org.twuni.common.validation.ValidationException;
import org.twuni.common.validation.Validator;

public class ContentLengthValidator implements Validator<String> {

	private static final Pattern PATTERN = Pattern.compile( "^Content-Length: (.+)$" );

	private final Validator<Integer> validator;

	public ContentLengthValidator( int maximumContentLength ) {
		validator = new RangeValidator( maximumContentLength );
	}

	@Override
	public void validate( String input ) {
		Matcher matcher = PATTERN.matcher( input );
		if( matcher.matches() ) {
			try {
				Integer length = Integer.valueOf( matcher.group( 1 ) );
				validator.validate( length );
			} catch( NumberFormatException exception ) {
				throw new ValidationException();
			}
		}
	}

}
