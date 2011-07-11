package org.twuni.net.http.response;

import org.twuni.net.http.Entity;
import org.twuni.net.http.Header;
import org.twuni.net.http.Message;

public class Response extends Message {

	private final Status status;

	public Response( Status status, String body ) {
		this( status, "text/plain", body );
	}

	public Response( Status status, String contentType, String body ) {
		this( status, new Entity( contentType, body ) );
	}

	public Response( Status status, Entity entity ) {
		this( status );
		setEntity( entity );
	}

	public Response( Status status ) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format( "%s: {status=%s, version=%s, headers=%s, body=%s}", getClass().getSimpleName(), status, Float.toString( version ), headers, body );
	}

	public Status getStatus() {
		return status;
	}

	public void setEntity( Entity entity ) {

		headers.put( Header.CONTENT_TYPE, entity.getType() );
		headers.put( Header.CONTENT_LENGTH, Integer.toString( entity.getContentLength() ) );

		setBody( entity.getContent() );

	}

}
