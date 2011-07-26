package org.twuni.common.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.LoggerFactory;
import org.twuni.common.Filter;
import org.twuni.common.FilterChain;
import org.twuni.common.log.NamedLogger;
import org.twuni.common.net.exception.ConnectionClosedException;
import org.twuni.common.net.http.request.Request;
import org.twuni.common.net.http.responder.Responder;
import org.twuni.common.net.http.response.Response;

public class Worker extends Thread {

	private final RequestReader reader = new RequestReader();
	private final ResponseWriter writer = new ResponseWriter();

	private final NamedLogger log;
	private final Socket socket;
	private final Responder responder;
	private final Filter<Response> filter;

	public Worker( Socket socket, Responder responder ) {
		this( socket, responder, new FilterChain<Response>() );
	}

	public Worker( Socket socket, Responder responder, Filter<Response> filter ) {

		super( String.format( "[%s] [HTTP] [%s] [%s]", Integer.valueOf( socket.getLocalPort() ), socket.getInetAddress().getHostAddress(), Integer.valueOf( socket.getPort() ) ) );

		this.log = new NamedLogger( LoggerFactory.getLogger( getClass() ), getName() );
		this.socket = socket;
		this.responder = responder;
		this.filter = filter;

	}

	@Override
	public void run() {

		try {

			while( true ) {
				handle( socket.getInputStream(), socket.getOutputStream() );
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
		log.info( String.format( "-> %s %s %s", request.getMethod(), request.getResource(), new String( request.getContent() ) ) );

		Response response = responder.respondTo( request );

		response = filter.filter( response );

		log.debug( String.format( "<< %s", response ) );
		log.info( String.format( "<- %s", response.getStatus() ) );

		writer.write( response, out );

	}

	public void close() {
		try {
			socket.close();
		} catch( IOException ignore ) {
		}
	}

}
