package org.twuni.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.net.http.request.Request;
import org.twuni.net.http.response.Response;

public class SocketHandler extends Thread {

	private final Logger log = LoggerFactory.getLogger( getClass() );

	private final Socket socket;
	private final RequestReader reader;
	private final RequestHandler handler;
	private final ResponseWriter writer;

	public SocketHandler( Socket socket, RequestReader reader, RequestHandler handler, ResponseWriter writer ) {

		super( String.format( "[http-%s][%s][%s]", Integer.valueOf( socket.getLocalPort() ), socket.getInetAddress(), Integer.valueOf( socket.getPort() ) ) );

		this.socket = socket;
		this.reader = reader;
		this.handler = handler;
		this.writer = writer;

	}

	@Override
	public void run() {

		try {

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			Request request = reader.read( in );
			log.debug( String.format( "%s << %s", getName(), request ) );

			in.close();

			Response response = handler.respondTo( request );
			log.debug( String.format( "%s >> %s", getName(), response ) );

			writer.write( response, out );

			socket.close();

		} catch( UnsupportedOperationException exception ) {
			log.warn( exception.getMessage() );
		} catch( IOException exception ) {
			log.error( "An unknown error occurred.", exception );
		}

	}

}
