package org.twuni.net.http.response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Response {

	private final ResponseStatus status;
	private final Map<String, Set<String>> headers = new HashMap<String, Set<String>>();
	private final float version;

	public Response( ResponseStatus status, float version ) {
		this.status = status;
		this.version = version;
	}

	public void addHeader( String name, String value ) {
		Set<String> header = headers.get( name );
		if( header == null ) {
			header = new HashSet<String>();
			headers.put( name, header );
		}
		header.add( value );
	}

	@Override
	public String toString() {
		return String.format( "%s: { status: %s, version: %s, headers: %s }", getClass().getSimpleName(), status, Float.toString( version ), headers );
	}

	public Map<String, Set<String>> getHeaders() {
		return headers;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public float getVersion() {
		return version;
	}

}
