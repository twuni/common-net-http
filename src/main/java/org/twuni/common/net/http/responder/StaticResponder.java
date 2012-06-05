package org.twuni.common.net.http.responder;

import org.twuni.common.net.http.request.Request;
import org.twuni.common.net.http.response.Response;
import org.twuni.common.net.http.response.Status;

public class StaticResponder implements Responder {

	private final Response response;

	public StaticResponder( Status status ) {
		response = new Response( status );
	}

	public StaticResponder( Status status, String body ) {
		response = new Response( status, body );
	}

	public StaticResponder( Status status, String contentType, String body ) {
		response = new Response( status, contentType, body );
	}

	public StaticResponder( String body ) {
		this( Status.OK, body );
	}

	public StaticResponder( String contentType, String body ) {
		this( Status.OK, contentType, body );
	}

	@Override
	public Response respondTo( Request request ) {
		return response;
	}

}
