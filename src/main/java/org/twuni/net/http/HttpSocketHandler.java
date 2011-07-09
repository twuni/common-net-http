package org.twuni.net.http;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpSocketHandler extends Thread {

	private static final Pattern HEADER = Pattern.compile( "^([^:]+): (.+)$" );

	private final Logger log = LoggerFactory.getLogger( getClass() );

	private final Socket socket;

	public HttpSocketHandler( Socket socket ) {
		super( String.format( "[http-%s][%s][%s]", Integer.valueOf( socket.getLocalPort() ), socket.getInetAddress(), Integer.valueOf( socket.getPort() ) ) );
		this.socket = socket;
	}

	@Override
	public void run() {

		try {

			Scanner scanner = new Scanner( socket.getInputStream() );
			HttpRequest request = null;

			while( scanner.hasNextLine() ) {

				String line = scanner.nextLine();
				log.debug( String.format( "%s << %s", getName(), line ) );

				if( request == null ) {
					String [] preamble = line.split( " " );
					request = new HttpRequest( HttpMethod.valueOf( preamble[0] ), preamble[1], Float.parseFloat( preamble[2].split( "/" )[1] ) );
					continue;
				}

				if( "".equals( line ) || line == null ) {
					break;
				}

				Matcher matcher = HEADER.matcher( line );
				request.addHeader( matcher.replaceAll( "$1" ), matcher.replaceAll( "$2" ) );

			}

			// TODO: For certain methods, read the request body.

			socket.close();

		} catch( IOException exception ) {
			log.error( "An unknown error occurred.", exception );
		}

	}

}
