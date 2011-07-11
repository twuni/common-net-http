package org.twuni.net.http;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.twuni.net.Factory;
import org.twuni.net.http.request.Request;
import org.twuni.net.http.response.Response;
import org.twuni.net.http.response.Status;

public class SocketHandlerFactory implements Factory<SocketHandler> {

	private final RequestReader reader = new RequestReader();
	private final ResponseWriter writer = new ResponseWriter();
	private final RequestHandler handler = new RequestHandler() {

		private final DateFormat dateFormat = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss z" );

		@Override
		public Response respondTo( Request request ) {

			Response response = new Response( Status.OK );

			String body = "There is nothing here. Go away.";

			response.addHeader( "Date", dateFormat.format( new Date() ) );
			response.addHeader( "Content-Type", "text/plain; charset=UTF-8" );
			response.addHeader( "Content-Length", Integer.toString( body.getBytes().length ) );

			response.setBody( body );

			return response;

		}

	};

	@Override
	public SocketHandler createInstance( Object... args ) {

		if( args.length != 1 ) {
			throw new IllegalArgumentException();
		}

		return new SocketHandler( (Socket) args[0], reader, handler, writer );

	}

}
