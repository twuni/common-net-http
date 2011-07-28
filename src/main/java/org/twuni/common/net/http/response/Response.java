package org.twuni.common.net.http.response;

import static org.twuni.common.net.http.util.MimeUtils.getContentType;

import org.twuni.common.net.http.Header;
import org.twuni.common.net.http.Message;
import org.twuni.common.util.Base64;

public class Response extends Message {

	private final Status status;

	public Response( Status status, byte [] content ) {
		this( status );
		setContent( content );
	}

	public Response( Status status, String content ) {
		this( status );
		setContent( content );
	}

	public Response( Status status, String contentType, byte [] content ) {
		this( status );
		setContent( content, contentType );
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

	private void setContent( byte [] content ) {
		setContent( content, getContentType( content ) );
	}

	private void setContent( String content ) {
		setContent( content.getBytes() );
	}

	private void setContent( String content, String type ) {
		setContent( content.getBytes(), type );
	}

	private void setContent( byte [] content, String type ) {
		this.content = content;
		headers.put( Header.CONTENT_TYPE, type );
		headers.put( Header.CONTENT_LENGTH, Integer.toString( content.length ) );
	}

	@Override
	public String toString() {
		return String.format( "%s: {status=%s, version=%s, headers=%s, content=%s}", getClass().getSimpleName(), status, Float.toString( version ), headers, Base64.encode( content ) );
	}

}
