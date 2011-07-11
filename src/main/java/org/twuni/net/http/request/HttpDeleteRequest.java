package org.twuni.net.http.request;

import org.twuni.net.http.HttpMethod;

public class HttpDeleteRequest extends HttpRequest {

	public HttpDeleteRequest( String resource, float version ) {
		super( HttpMethod.DELETE, resource, version );
	}

}
