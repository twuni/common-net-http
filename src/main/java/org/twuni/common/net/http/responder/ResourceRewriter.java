package org.twuni.common.net.http.responder;

import java.util.ArrayList;
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
		List<String> to = new ArrayList<String>();
		to.addAll( Arrays.asList( mappings ) );
		mapping.put( resource, to );
	}

	@Override
	public Response respondTo( Request request ) {

		List<String> redirects = getRedirects( request.getResource() );

		Response response = new Response( Status.NOT_FOUND );

		for( String redirect : redirects ) {
			response = responder.respondTo( new Request( request.getMethod(), redirect, request.getVersion() ) );
			if( !Status.NOT_FOUND.equals( response.getStatus() ) ) {
				break;
			}
		}

		return response;

	}

	protected List<String> getRedirects( String resource ) {

		List<String> redirects = mapping.get( resource );

		if( redirects == null ) {
			redirects = new ArrayList<String>();
		}

		redirects.add( 0, resource );

		return redirects;

	}

}
