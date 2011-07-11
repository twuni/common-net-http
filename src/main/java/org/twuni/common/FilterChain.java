package org.twuni.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FilterChain<T> implements Filter<T> {

	private final List<Filter<T>> filters = new ArrayList<Filter<T>>();

	public FilterChain() {
	}

	public FilterChain( Filter<T>... filters ) {
		this.filters.addAll( Arrays.asList( filters ) );
	}

	public void add( Filter<T> filter ) {
		filters.add( filter );
	}

	@Override
	public T filter( T original ) {
		T filtered = original;
		for( Filter<T> filter : filters ) {
			filtered = filter.filter( filtered );
		}
		return filtered;
	}

}
