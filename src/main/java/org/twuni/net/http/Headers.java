package org.twuni.net.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Headers {

	private final Map<String, Set<String>> headers = new HashMap<String, Set<String>>();

	public void put( Header name, String value ) {
		put( name.toString(), value );
	}

	public void put( String name, String value ) {
		headers.put( name, null );
		add( name, value );
	}

	public void add( Header name, String value ) {
		add( name.toString(), value );
	}

	public void add( String name, String value ) {
		Set<String> header = getAll( name );
		if( header.isEmpty() ) {
			header = new HashSet<String>();
			headers.put( name, header );
		}
		header.add( value );
	}

	public String get( Header name ) {
		return get( name.toString() );
	}

	public String get( Header name, String defaultValue ) {
		return get( name.toString(), defaultValue );
	}

	public String get( String name ) {
		return get( name, "" );
	}

	public String get( String name, String defaultValue ) {
		Set<String> header = getAll( name );
		if( !header.isEmpty() ) {
			return header.iterator().next();
		}
		return defaultValue;
	}

	public Map<String, Set<String>> getAll() {
		return headers;
	}

	public Set<String> getAll( Header name ) {
		return getAll( name.toString() );
	}

	public Set<String> getAll( String name ) {
		Set<String> header = headers.get( name );
		if( header != null ) {
			return header;
		}
		return Collections.emptySet();
	}

	public Set<String> keySet() {
		return headers.keySet();
	}

	@Override
	public String toString() {
		return headers.toString();
	}

}
