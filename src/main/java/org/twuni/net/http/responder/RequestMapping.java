package org.twuni.net.http.responder;

import java.util.HashMap;
import java.util.Map;

import org.twuni.net.http.Method;
import org.twuni.net.http.request.Request;
import org.twuni.net.http.response.Response;
import org.twuni.net.http.response.Status;

public class RequestMapping implements Responder {

	private final Map<String, Map<Method, Responder>> handlers = new HashMap<String, Map<Method, Responder>>();

	public void map( Method method, String pattern, Responder handler ) {

		Map<Method, Responder> methods = handlers.get( pattern );

		if( methods == null ) {
			methods = new HashMap<Method, Responder>();
			handlers.put( pattern, methods );
		}

		methods.put( method, handler );

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

	@Override
	public Response respondTo( Request request ) {
		return lookup( request.getMethod(), request.getResource() ).respondTo( request );
	}

}
