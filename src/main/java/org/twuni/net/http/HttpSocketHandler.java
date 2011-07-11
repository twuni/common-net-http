package org.twuni.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.net.http.request.HttpRequest;
import org.twuni.net.http.response.HttpResponse;

public class HttpSocketHandler extends Thread {

	private final Logger log = LoggerFactory.getLogger( getClass() );

	private final Socket socket;
	private final HttpRequestReader reader;
	private final HttpRequestHandler handler;
	private final HttpResponseWriter writer;

	public HttpSocketHandler( Socket socket, HttpRequestReader reader, HttpRequestHandler handler, HttpResponseWriter writer ) {

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

			HttpRequest request = reader.read( in );
			log.debug( String.format( "%s << %s", getName(), request ) );

			in.close();

			HttpResponse response = handler.respondTo( request );
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
