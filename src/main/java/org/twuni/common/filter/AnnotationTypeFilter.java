package org.twuni.common.filter;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.common.Filter;

public class AnnotationTypeFilter implements Filter<Collection<Class<?>>> {

	private final Logger log = LoggerFactory.getLogger( getClass() );

	private final Class<? extends Annotation> annotationType;

	public AnnotationTypeFilter( Class<? extends Annotation> annotationType ) {
		this.annotationType = annotationType;
	}

	@Override
	public Collection<Class<?>> filter( Collection<Class<?>> types ) {

		Collection<Class<?>> filtered = new ArrayList<Class<?>>();

		for( Class<?> type : types ) {

			if( !type.isAnnotationPresent( annotationType ) ) {
				log.info( String.format( "Skipped %s: No annotation found of type %s", type.getName(), annotationType.getName() ) );
				continue;
			}

			filtered.add( type );

		}

		return filtered;

	}

}
