package org.twuni.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.net.exception.ConnectionClosedException;
import org.twuni.net.http.request.DeleteRequest;
import org.twuni.net.http.request.GetRequest;
import org.twuni.net.http.request.PostRequest;
import org.twuni.net.http.request.PutRequest;
import org.twuni.net.http.request.Request;

public class RequestReader {

	private static final Pattern HEADER = Pattern.compile( "^([^:]+): (.+)$" );

	private final Logger log = LoggerFactory.getLogger( getClass() );

	public Request read( InputStream from ) throws IOException {

		Request request = null;
		BufferedReader reader = new BufferedReader( new InputStreamReader( from ) );

		for( String line = reader.readLine(); line != null && !"".equals( line ); line = reader.readLine() ) {

			log.debug( line );

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
			reader.read( buffer );
			request.setBody( new String( buffer ) );
		}

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
