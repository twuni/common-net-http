package org.twuni.net.http.request;

import org.twuni.net.http.HttpMethod;

public class HttpPutRequest extends HttpRequest {

	public HttpPutRequest( String resource, float version ) {
		super( HttpMethod.PUT, resource, version );
	}

}
