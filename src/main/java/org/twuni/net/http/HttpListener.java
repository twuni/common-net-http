package org.twuni.net.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpListener extends Thread {

	private final Logger log = LoggerFactory.getLogger( getClass() );

	private final int port;

	public HttpListener( int port ) {
		super( String.format( "http-%s", Integer.valueOf( port ) ) );
		this.port = port;
	}

	@Override
	public void run() {

		try {

			ServerSocket server = new ServerSocket( port );

			while( server.isBound() ) {

				try {

					Socket socket = server.accept();
					Thread handler = new HttpSocketHandler( socket );
					handler.start();

				} catch( IOException exception ) {
					log.warn( "An error occurred while attempting to accept a connection.", exception );
				}

			}

		} catch( IOException exception ) {
			log.error( String.format( "Unable to bind to port %s", Integer.valueOf( port ) ), exception );
		}
	}

}
