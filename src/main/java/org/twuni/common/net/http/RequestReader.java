package org.twuni.common.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.common.net.exception.ConnectionClosedException;
import org.twuni.common.net.http.exception.UnsupportedMethodException;
import org.twuni.common.net.http.request.Request;

final class RequestReader {

	private static final Pattern HEADER = Pattern.compile( "^([^:]+): (.+)$" );

	private final Logger log = LoggerFactory.getLogger( getClass() );

	public Request read( InputStream from ) throws IOException {

		Request request = null;
		BufferedReader reader = new BufferedReader( new InputStreamReader( from ) );

		for( String line = reader.readLine(); line != null && !"".equals( line ); line = reader.readLine() ) {

			if( request == null ) {

				String [] preamble = line.split( " " );

				try {

					final Method method = Method.valueOf( preamble[0] );
					final String resource = preamble[1];
					final float version = Float.parseFloat( preamble[2].split( "/" )[1] );

					request = toHttpRequest( method, resource, version );

				} catch( IllegalArgumentException exception ) {
					log.warn( String.format( "Discarding junk line: %s", line ) );
					continue;
				}

				continue;

			}

			if( "".equals( line ) || line == null ) {
				break;
			}

			Matcher matcher = HEADER.matcher( line );

			request.getHeaders().add( matcher.replaceAll( "$1" ), matcher.replaceAll( "$2" ) );

		}

		if( request == null ) {
			throw new ConnectionClosedException( "The connection was closed by the client." );
		}

		extractRequestBody( reader, request );

		return request;

	}

	private void extractRequestBody( Reader reader, Request request ) throws IOException {

		int length = Integer.parseInt( request.getHeaders().get( "Content-Length", "0" ) );

		if( length > 0 ) {
			char [] buffer = new char [length];
			for( int read = 0; read < length; read += reader.read( buffer, read, buffer.length - read ) ) {
			}
			request.setContent( new String( buffer ).getBytes() );
		}

	}

	private Request toHttpRequest( Method method, String resource, float version ) {

		Request request = new Request( method, resource, version );

		switch( method ) {
			case GET:
			case POST:
			case PUT:
			case DELETE:
			case HEAD:
			case OPTIONS:
			case CONNECT:
			case TRACE:
				return request;
		}

		throw new UnsupportedMethodException( method );

	}

}
