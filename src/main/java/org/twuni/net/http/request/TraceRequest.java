package org.twuni.net.http.request;

import org.twuni.net.http.Method;

public class TraceRequest extends Request {

	public TraceRequest( String resource ) {
		this( resource, VERSION );
	}

	public TraceRequest( String resource, float version ) {
		super( Method.TRACE, resource, version );
	}

}
