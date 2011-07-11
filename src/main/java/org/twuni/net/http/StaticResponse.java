package org.twuni.net.http;

import org.twuni.net.http.request.Request;
import org.twuni.net.http.response.Response;
import org.twuni.net.http.response.Status;

public class StaticResponse implements RequestHandler {

	private final Response response;

	public StaticResponse( Status status, String contentType, String body ) {
		this.response = new Response( status, contentType, body );
	}

	public StaticResponse( Status status, String body ) {
		this.response = new Response( status, body );
	}

	public StaticResponse( Status status ) {
		this.response = new Response( status );
	}

	@Override
	public Response respondTo( Request request ) {
		return response;
	}

}
