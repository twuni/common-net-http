package org.twuni.common.filter;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.twuni.common.Filter;
import org.twuni.common.FilterChain;

public class AnnotationFilter implements Filter<Collection<Class<?>>> {

	private final Class<? extends Annotation> annotationType;
	private final Class<?> parentType;

	public AnnotationFilter( Class<? extends Annotation> annotationType, Class<?> parentType ) {
		this.annotationType = annotationType;
		this.parentType = parentType;
	}

	@Override
	public Collection<Class<?>> filter( Collection<Class<?>> types ) {

		FilterChain<Collection<Class<?>>> filters = new FilterChain<Collection<Class<?>>>();

		filters.add( new AnnotationTypeFilter( annotationType ) );
		filters.add( new ParentTypeFilter( parentType ) );

		return filters.filter( types );

	}

}