package org.twuni.net.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Message {

	public static final float VERSION = 1.1f;
	public static final String LINE_SEPARATOR = "\n";

	protected final float version;
	protected final Map<String, Set<String>> headers = new HashMap<String, Set<String>>();
	protected String body = "";

	protected Message() {
		this( VERSION );
	}

	protected Message( float version ) {
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

	public String getHeader( Header name ) {
		return getHeader( name.toString() );
	}

	public String getHeader( Header name, String defaultValue ) {
		return getHeader( name.toString(), defaultValue );
	}

	public String getHeader( String name ) {
		return getHeader( name, "" );
	}

	public String getHeader( String name, String defaultValue ) {
		Set<String> header = getHeaders( name );
		if( !header.isEmpty() ) {
			return header.iterator().next();
		}
		return defaultValue;
	}

	public Map<String, Set<String>> getHeaders() {
		return headers;
	}

	public Set<String> getHeaders( Header name ) {
		return getHeaders( name.toString() );
	}

	public Set<String> getHeaders( String name ) {
		Set<String> header = headers.get( name );
		if( header != null ) {
			return header;
		}
		return Collections.emptySet();
	}

	public String getBody() {
		return body;
	}

	public float getVersion() {
		return version;
	}

	public void setBody( String body ) {
		this.body = body;
	}

}
