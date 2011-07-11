package org.twuni.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.twuni.net.exception.ConnectionClosedException;
import org.twuni.net.http.exception.UnsupportedMethodException;
import org.twuni.net.http.request.Request;
import org.twuni.net.http.response.Response;
import org.twuni.net.http.response.Status;
import org.twuni.net.log.NamedLogger;

public class SocketHandler extends Thread {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss z" );

	private final NamedLogger log;

	private final Socket socket;
	private final RequestReader reader;
	private final RequestHandler handler;
	private final ResponseWriter writer;

	public SocketHandler( Socket socket, RequestReader reader, RequestHandler handler, ResponseWriter writer ) {

		super( String.format( "[%s] [HTTP] [%s] [%s]", Integer.valueOf( socket.getLocalPort() ), socket.getInetAddress().getHostAddress(), Integer.valueOf( socket.getPort() ) ) );

		this.log = new NamedLogger( LoggerFactory.getLogger( getClass() ), getName() );
		this.socket = socket;
		this.reader = reader;
		this.handler = handler;
		this.writer = writer;

	}

	@Override
	public void run() {

		try {

			while( true ) {
				try {
					handle( socket.getInputStream(), socket.getOutputStream() );
				} catch( UnsupportedMethodException exception ) {
					log.info( exception.getMessage() );
					writer.write( new Response( Status.METHOD_NOT_ALLOWED ), socket.getOutputStream() );
				}
			}

		} catch( ConnectionClosedException exception ) {
			log.debug( exception.getMessage() );
		} catch( IOException exception ) {
			log.info( exception.getMessage() );
		}

		close();

	}

	private void handle( InputStream in, OutputStream out ) throws IOException {

		Request request = reader.read( in );
		log.debug( String.format( ">> %s", request ) );

		Response response = handler.respondTo( request );
		log.debug( String.format( "<< %s", response ) );

		augmentResponse( response );

		writer.write( response, out );

		inspectResponse( response );

	}

	private void augmentResponse( Response response ) {
		response.getHeaders().put( Header.DATE, DATE_FORMAT.format( new Date() ) );
	}

	private void inspectResponse( Response response ) throws IOException {

		if( response.getHeaders().get( Header.CONNECTION ).equals( "close" ) ) {
			throw new ConnectionClosedException( "The connection was closed by the server." );
		}

	}

	public void close() {
		try {
			socket.close();
		} catch( IOException ignore ) {
		}
	}

}
