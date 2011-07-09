package org.twuni.net.http;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HttpRequest {

	private final HttpMethod method;
	private final String resource;
	private final float version;
	private final Map<String, Set<String>> headers = new HashMap<String, Set<String>>();

	public HttpRequest( HttpMethod method, String resource, float version ) {
		this.method = method;
		this.resource = resource;
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

	public HttpMethod getMethod() {
		return method;
	}

	public String getResource() {
		return resource;
	}

	public float getVersion() {
		return version;
	}

}
