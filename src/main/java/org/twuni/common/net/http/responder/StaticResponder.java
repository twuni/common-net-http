package org.twuni.common.net.http.responder;

import org.twuni.common.net.http.request.Request;
import org.twuni.common.net.http.response.Response;
import org.twuni.common.net.http.response.Status;

public class StaticResponder implements Responder {

	private final Response response;

	public StaticResponder( Status status, String contentType, String body ) {
		this.response = new Response( status, contentType, body );
	}

	public StaticResponder( Status status, String body ) {
		this.response = new Response( status, body );
	}

	public StaticResponder( Status status ) {
		this.response = new Response( status );
	}

	@Override
	public Response respondTo( Request request ) {
		return response;
	}

}
