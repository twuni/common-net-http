package org.twuni.common.net.http.response;

import org.twuni.common.net.http.Header;
import org.twuni.common.net.http.Message;

public class Response extends Message {

	private final Status status;

	public Response( Status status, String content ) {
		this( status );
		setContent( content );
	}

	public Response( Status status, String contentType, String content ) {
		this( status );
		setContent( content, contentType );
	}

	public Response( Status status ) {
		this.status = status;
		setContent( "" );
	}

	public Status getStatus() {
		return status;
	}

	private void setContent( String content ) {
		setContent( content, "text/plain" );
	}

	private void setContent( String content, String type ) {
		this.content = content;
		headers.put( Header.CONTENT_TYPE, type );
		headers.put( Header.CONTENT_LENGTH, Integer.toString( content.getBytes().length ) );
	}

	@Override
	public String toString() {
		return String.format( "%s: {status=%s, version=%s, headers=%s, content=%s}", getClass().getSimpleName(), status, Float.toString( version ), headers, content );
	}

}
