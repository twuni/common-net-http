package org.twuni.net.http;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.twuni.net.Factory;
import org.twuni.net.http.request.HttpRequest;
import org.twuni.net.http.response.HttpResponse;
import org.twuni.net.http.response.HttpResponseStatus;

public class HttpSocketHandlerFactory implements Factory<HttpSocketHandler> {

	private final HttpRequestReader reader = new HttpRequestReader();
	private final HttpResponseWriter writer = new HttpResponseWriter();
	private final HttpRequestHandler handler = new HttpRequestHandler() {

		private final DateFormat dateFormat = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss z" );

		@Override
		public HttpResponse respondTo( HttpRequest request ) {

			HttpResponse response = new HttpResponse( HttpResponseStatus.OK, request.getVersion() );

			response.addHeader( "Date", dateFormat.format( new Date() ) );
			response.addHeader( "Content-Type", "text/plain; charset=UTF-8" );
			response.addHeader( "Transfer-Encoding", "chunked" );

			return response;

		}

	};

	@Override
	public HttpSocketHandler createInstance( Object... args ) {

		if( args.length != 1 ) {
			throw new IllegalArgumentException();
		}

		return new HttpSocketHandler( (Socket) args[0], reader, handler, writer );

	}

}
