package org.twuni.net.http.request;

import org.twuni.net.http.Method;

public class GetRequest extends Request {

	public GetRequest( String resource, float version ) {
		super( Method.GET, resource, version );
	}

}
