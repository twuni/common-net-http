package org.twuni.common.net.http.request;

import org.twuni.common.net.http.Method;

public class ConnectRequest extends Request {

	public ConnectRequest( String resource ) {
		this( resource, VERSION );
	}

	public ConnectRequest( String resource, float version ) {
		super( Method.CONNECT, resource, version );
	}

}
