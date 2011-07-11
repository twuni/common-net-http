package org.twuni.common.net.http.request;

import org.twuni.common.net.http.Method;

public class GetRequest extends Request {

	public GetRequest( String resource ) {
		this( resource, VERSION );
	}

	public GetRequest( String resource, float version ) {
		super( Method.GET, resource, version );
	}

}
