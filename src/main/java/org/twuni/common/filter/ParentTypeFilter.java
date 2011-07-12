package org.twuni.common.filter;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.common.Filter;

public class ParentTypeFilter implements Filter<Collection<Class<?>>> {

	private final Logger log = LoggerFactory.getLogger( getClass() );

	private final Class<?> parentType;

	public ParentTypeFilter( Class<?> parentType ) {
		this.parentType = parentType;
	}

	@Override
	public Collection<Class<?>> filter( Collection<Class<?>> types ) {

		Collection<Class<?>> filtered = new ArrayList<Class<?>>();

		for( Class<?> type : types ) {

			if( !parentType.isAssignableFrom( type ) ) {
				log.info( String.format( "Skipped %s: Not an instance of %s", type.getName(), parentType.getName() ) );
				continue;
			}

			filtered.add( type );

		}

		return filtered;

	}

}
