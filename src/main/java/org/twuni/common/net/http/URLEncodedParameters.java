package org.twuni.common.net.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.twuni.common.MultiValueMap;

public class URLEncodedParameters extends MultiValueMap<String, String> {

	private static final String UTF_8 = "UTF-8";
	
	public URLEncodedParameters() {
	}

	public URLEncodedParameters( String parameters ) {
		for( String pair : parameters.split( "&" ) ) {
			int index = pair.indexOf( '=' );
			String key = decode( pair.substring( 0, index ) );
			String value = decode( pair.substring( index + 1 ) );
			add( key, value );
		}
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		for( String key : keySet() ) {
			for( String value : getAll( key ) ) {
				if( string.length() > 0 ) {
					string.append( "&" );
				}
				string.append( encode( key ) );
				string.append( "=" );
				string.append( encode( value ) );
			}
		}
		return string.toString();
	}

	private String decode( String encoded ) {
		try {
			return URLDecoder.decode( encoded, UTF_8 );
		} catch( UnsupportedEncodingException exception ) {
			return encoded;
		}
	}

	private String encode( String decoded ) {
		try {
			return URLEncoder.encode( decoded, UTF_8 );
		} catch( UnsupportedEncodingException exception ) {
			return decoded;
		}
	}

}
