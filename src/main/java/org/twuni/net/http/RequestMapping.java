package org.twuni.net.http;

import java.util.HashMap;
import java.util.Map;

import org.twuni.net.http.request.Request;
import org.twuni.net.http.response.Response;
import org.twuni.net.http.response.Status;

public class RequestMapping implements RequestHandler {

	private final Map<String, Map<Method, RequestHandler>> handlers = new HashMap<String, Map<Method, RequestHandler>>();

	public void map( Method method, String pattern, RequestHandler handler ) {

		Map<Method, RequestHandler> methods = handlers.get( pattern );

		if( methods == null ) {
			methods = new HashMap<Method, RequestHandler>();
			handlers.put( pattern, methods );
		}

		methods.put( method, handler );

	}

	private RequestHandler lookup( Method method, String resource ) {

		Status status = Status.NOT_FOUND;

		for( String pattern : handlers.keySet() ) {

			if( !resource.matches( pattern ) ) {
				continue;
			}

			Map<Method, RequestHandler> methods = handlers.get( pattern );
			RequestHandler handler = methods.get( method );

			if( handler == null ) {
				status = Status.METHOD_NOT_ALLOWED;
				continue;
			}

			return handler;

		}

		return new StaticResponse( status );

	}

	@Override
	public Response respondTo( Request request ) {
		return lookup( request.getMethod(), request.getResource() ).respondTo( request );
	}

}
