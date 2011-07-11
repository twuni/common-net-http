package org.twuni.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implements common operations performed on multi-valued maps.
 */
public class MultiValueMap<K, V> {

	private final Map<K, Set<V>> map;

	public MultiValueMap() {
		this( new HashMap<K, Set<V>>() );
	}

	public MultiValueMap( Map<K, Set<V>> map ) {
		this.map = map;
	}

	public void put( K name, V value ) {
		map.put( name, null );
		add( name, value );
	}

	public void add( K name, V value ) {
		Set<V> values = getAll( name );
		if( values.isEmpty() ) {
			values = new HashSet<V>();
			map.put( name, values );
		}
		values.add( value );
	}

	public V get( K name ) {
		return get( name, null );
	}

	public V get( K name, V defaultValue ) {
		Set<V> values = getAll( name );
		if( !values.isEmpty() ) {
			return values.iterator().next();
		}
		return defaultValue;
	}

	public Map<K, Set<V>> getAll() {
		return map;
	}

	public Set<V> getAll( K name ) {
		Set<V> values = map.get( name );
		if( values != null ) {
			return values;
		}
		return Collections.emptySet();
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public String toString() {
		return map.toString();
	}

}
