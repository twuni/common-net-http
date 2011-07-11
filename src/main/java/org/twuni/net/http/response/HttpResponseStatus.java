package org.twuni.net.http.response;

public enum HttpResponseStatus {

	OK( 200 );

	private final int code;

	private HttpResponseStatus( int code ) {
		this.code = code;
	}

	@Override
	public String toString() {
		return String.format( "%s %s", Integer.valueOf( code ), name() );
	}

}
