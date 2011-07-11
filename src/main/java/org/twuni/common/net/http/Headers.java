package org.twuni.common.net.http;

import java.util.Set;

import org.twuni.common.MultiValueMap;

public class Headers extends MultiValueMap<String, String> {

	public void put( Header name, String value ) {
		put( name.toString(), value );
	}

	public void add( Header name, String value ) {
		add( name.toString(), value );
	}

	public String get( Header name ) {
		return get( name.toString() );
	}

	public String get( Header name, String defaultValue ) {
		return get( name.toString(), defaultValue );
	}

	public Set<String> getAll( Header name ) {
		return getAll( name.toString() );
	}

	@Override
	public String get( String name ) {
		return get( name, "" );
	}

}
