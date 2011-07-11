package org.twuni.net.http.response;

import org.twuni.net.http.Header;
import org.twuni.net.http.Message;

public class Response extends Message {

	private final Status status;

	public Response( Status status, String body ) {
		this( status );
		setBody( body );
	}

	public Response( Status status, String contentType, String body ) {
		this( status );
		setBody( contentType, body );
	}

	public Response( Status status ) {
		this.status = status;
		setBody( "" );
	}

	public Status getStatus() {
		return status;
	}

	private void setBody( String body ) {
		setBody( body, "text/plain" );
	}

	private void setBody( String body, String type ) {
		this.body = body;
		headers.put( Header.CONTENT_TYPE, type );
		headers.put( Header.CONTENT_LENGTH, Integer.toString( body.getBytes().length ) );
	}

	@Override
	public String toString() {
		return String.format( "%s: {status=%s, version=%s, headers=%s, body=%s}", getClass().getSimpleName(), status, Float.toString( version ), headers, body );
	}

}
