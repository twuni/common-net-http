package org.twuni.net.http.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.twuni.net.http.Method;

public abstract class Request {

	private final Method method;
	private final String resource;
	private final float version;
	private final Map<String, Set<String>> headers = new HashMap<String, Set<String>>();

	protected Request( Method method, String resource, float version ) {
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

	public String getHeader( String name ) {
		Set<String> header = getHeaders( name );
		if( !header.isEmpty() ) {
			return header.iterator().next();
		}
		return "";
	}

	public Set<String> getHeaders( String name ) {
		Set<String> header = headers.get( name );
		if( header != null ) {
			return header;
		}
		return Collections.emptySet();
	}

	public void parseBody( InputStream input ) throws IOException {
	}

	public Method getMethod() {
		return method;
	}

	public String getResource() {
		return resource;
	}

	public float getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return String.format( "%s: { method: %s, resource: %s, version: %s, headers: %s }", getClass().getSimpleName(), method, resource, Float.toString( version ), headers );
	}

}
