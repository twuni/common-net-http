package org.twuni.common.net.http.request;

import org.twuni.common.net.http.Method;

public class HeadRequest extends Request {

	public HeadRequest( String resource ) {
		this( resource, VERSION );
	}

	public HeadRequest( String resource, float version ) {
		super( Method.HEAD, resource, version );
	}

}
