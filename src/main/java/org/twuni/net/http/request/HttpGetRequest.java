package org.twuni.net.http.request;

import org.twuni.net.http.HttpMethod;

public class HttpGetRequest extends HttpRequest {

	public HttpGetRequest( String resource, float version ) {
		super( HttpMethod.GET, resource, version );
	}

}
