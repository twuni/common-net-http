package org.twuni.net.http.request;

import org.twuni.net.http.Method;

public class PutRequest extends Request {

	public PutRequest( String resource ) {
		this( resource, VERSION );
	}

	public PutRequest( String resource, float version ) {
		super( Method.PUT, resource, version );
	}

}
