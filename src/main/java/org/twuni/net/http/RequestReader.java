package org.twuni.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.twuni.net.http.request.DeleteRequest;
import org.twuni.net.http.request.GetRequest;
import org.twuni.net.http.request.PostRequest;
import org.twuni.net.http.request.PutRequest;
import org.twuni.net.http.request.Request;

public class RequestReader {

	private static final Pattern HEADER = Pattern.compile( "^([^:]+): (.+)$" );

	public Request read( InputStream from ) throws IOException {

		Request request = null;
		Scanner scanner = new Scanner( from );

		while( scanner.hasNextLine() ) {

			String line = scanner.nextLine();

			if( request == null ) {

				String [] preamble = line.split( " " );

				final Method method = Method.valueOf( preamble[0] );
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

	private Request toHttpRequest( Method method, String resource, float version ) {

		switch( method ) {
			case GET:
				return new GetRequest( resource, version );
			case POST:
				return new PostRequest( resource, version );
			case PUT:
				return new PutRequest( resource, version );
			case DELETE:
				return new DeleteRequest( resource, version );
		}

		throw new UnsupportedOperationException( String.format( "Method %s is not supported.", method ) );

	}

}
