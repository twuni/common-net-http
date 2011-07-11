package org.twuni.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.common.Factory;

public class SocketListener extends Thread {

	private final Logger log = LoggerFactory.getLogger( getClass() );

	private final int port;
	private final Factory<? extends Thread> workerThreadFactory;

	public SocketListener( int port, Factory<? extends Thread> workerThreadFactory ) {
		super( String.format( "%s-%s", workerThreadFactory.getClass().getSimpleName(), Integer.valueOf( port ) ) );
		this.port = port;
		this.workerThreadFactory = workerThreadFactory;
	}

	@Override
	public void run() {

		try {

			ServerSocket server = new ServerSocket( port );

			while( server.isBound() ) {

				try {

					Socket socket = server.accept();
					Thread worker = workerThreadFactory.createInstance( socket );
					worker.start();

				} catch( IOException exception ) {
					log.warn( "An error occurred while attempting to accept a connection.", exception );
				}

			}

		} catch( IOException exception ) {
			log.error( String.format( "Unable to bind to port %s", Integer.valueOf( port ) ), exception );
		}

	}

}
