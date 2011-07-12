package org.twuni.common.net.http.responder;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.common.filter.AnnotationFilter;
import org.twuni.common.net.http.Method;
import org.twuni.common.net.http.annotation.RespondsTo;
import org.twuni.common.net.http.request.Request;
import org.twuni.common.net.http.response.Response;
import org.twuni.common.net.http.response.Status;

public class RequestMapping implements Responder {

	private final Logger log = LoggerFactory.getLogger( getClass() );
	private final AnnotationFilter annotationFilter = new AnnotationFilter( RespondsTo.class, Responder.class );
	private final Map<String, Map<Method, Responder>> handlers = new HashMap<String, Map<Method, Responder>>();

	@SuppressWarnings( "unchecked" )
	public void map( Class<?>... types ) {

		Collection<Class<?>> filtered = annotationFilter.filter( Arrays.asList( types ) );

		for( Class<?> type : filtered ) {

			RespondsTo annotation = type.getAnnotation( RespondsTo.class );

			try {
				map( annotation.method(), annotation.value(), (Class<? extends Responder>) type );
			} catch( Exception exception ) {
				log.warn( String.format( "Mapping failed: %s %s %s", annotation.method(), annotation.value(), type.getName() ), exception );
			}

		}

	}

	public void map( Method method, String pattern, Class<? extends Responder> type ) throws InstantiationException, IllegalAccessException {
		map( method, pattern, type.newInstance() );
	}

	public void map( Method method, String pattern, Responder handler ) {

		Map<Method, Responder> methods = handlers.get( pattern );

		if( methods == null ) {
			methods = new HashMap<Method, Responder>();
			handlers.put( pattern, methods );
		}

		methods.put( method, handler );

	}

	@Override
	public Response respondTo( Request request ) {
		return lookup( request.getMethod(), request.getResource() ).respondTo( request );
	}

	private Responder lookup( Method method, String resource ) {

		Status status = Status.NOT_FOUND;

		for( String pattern : handlers.keySet() ) {

			if( !resource.matches( pattern ) ) {
				continue;
			}

			Map<Method, Responder> methods = handlers.get( pattern );
			Responder handler = methods.get( method );

			if( handler == null ) {
				status = Status.METHOD_NOT_ALLOWED;
				continue;
			}

			return handler;

		}

		return new StaticResponder( status );

	}

}
