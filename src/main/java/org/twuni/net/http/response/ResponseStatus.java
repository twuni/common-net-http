package org.twuni.net.http.response;

public enum ResponseStatus {

	OK( 200 );

	private final int code;

	private ResponseStatus( int code ) {
		this.code = code;
	}

	@Override
	public String toString() {
		return String.format( "%s %s", Integer.valueOf( code ), name() );
	}

}
