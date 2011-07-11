package org.twuni.net.http.response;

import org.twuni.net.http.Message;

public class Response extends Message {

	private final Status status;

	public Response( Status status ) {
		this.status = status;
	}

	public Response( Status status, float version ) {
		super( version );
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format( "%s: { status: %s, version: %s, headers: %s, body: \"%s\" }", getClass().getSimpleName(), status, Float.toString( version ), headers, body );
	}

	public Status getStatus() {
		return status;
	}

}
