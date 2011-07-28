package org.twuni.common.net.http.responder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.twuni.common.net.http.request.Request;
import org.twuni.common.net.http.response.Response;
import org.twuni.common.net.http.response.Status;

public class ResourceRewriter implements Responder {

	private final Responder responder;
	private final Map<String, List<String>> mapping = new HashMap<String, List<String>>();

	public ResourceRewriter( Responder responder ) {
		this.responder = responder;
	}

	public ResourceRewriter( Responder responder, String resource, String... mappings ) {
		this( responder );
		rewrite( resource, mappings );
	}

	public void rewrite( String resource, String... mappings ) {
		mapping.put( resource, Arrays.asList( mappings ) );
	}

	@Override
	public Response respondTo( Request request ) {
		List<String> candidates = mapping.get( request.getResource() );
		if( candidates == null ) {
			return responder.respondTo( request );
		}
		Response response = new Response( Status.NOT_FOUND );
		for( String candidate : candidates ) {
			response = responder.respondTo( new Request( request.getMethod(), candidate, request.getVersion() ) );
			if( !Status.NOT_FOUND.equals( response.getStatus() ) ) {
				return response;
			}
		}
		return response;
	}

}
