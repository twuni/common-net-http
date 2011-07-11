package org.twuni.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.twuni.net.http.request.HttpDeleteRequest;
import org.twuni.net.http.request.HttpGetRequest;
import org.twuni.net.http.request.HttpPostRequest;
import org.twuni.net.http.request.HttpPutRequest;
import org.twuni.net.http.request.HttpRequest;

public class HttpRequestReader {

	private static final Pattern HEADER = Pattern.compile( "^([^:]+): (.+)$" );

	public HttpRequest read( InputStream from ) throws IOException {

		HttpRequest request = null;
		Scanner scanner = new Scanner( from );

		while( scanner.hasNextLine() ) {

			String line = scanner.nextLine();

			if( request == null ) {

				String [] preamble = line.split( " " );

				final HttpMethod method = HttpMethod.valueOf( preamble[0] );
				final String resource = preamble[1];
				final float version = Float.parseFloat( preamble[2].split( "/" )[1] );

				request = toHttpRequest( method, resource, version );

				continue;

			}

			if( "".equals( line ) || line == null ) {
				break;
			}

			Matcher matcher = HEADER.matcher( line );
			request.addHeader( matcher.replaceAll( "$1" ), matcher.replaceAll( "$2" ) );

		}

		request.parseBody( from );

		return request;

	}

	private HttpRequest toHttpRequest( HttpMethod method, String resource, float version ) {

		switch( method ) {
			case GET:
				return new HttpGetRequest( resource, version );
			case POST:
				return new HttpPostRequest( resource, version );
			case PUT:
				return new HttpPutRequest( resource, version );
			case DELETE:
				return new HttpDeleteRequest( resource, version );
		}

		throw new UnsupportedOperationException( String.format( "Method %s is not supported.", method ) );

	}

}
