package org.twuni.net.http.request;

import org.twuni.net.http.Method;

public class DeleteRequest extends Request {

	public DeleteRequest( String resource ) {
		this( resource, VERSION );
	}

	public DeleteRequest( String resource, float version ) {
		super( Method.DELETE, resource, version );
	}

}
