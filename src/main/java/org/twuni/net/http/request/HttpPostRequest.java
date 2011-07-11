package org.twuni.net.http.request;

import org.twuni.net.http.HttpMethod;

public class HttpPostRequest extends HttpRequest {

	public HttpPostRequest( String resource, float version ) {
		super( HttpMethod.POST, resource, version );
	}

}
