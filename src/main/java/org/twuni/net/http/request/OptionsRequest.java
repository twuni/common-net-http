package org.twuni.net.http.request;

import org.twuni.net.http.Method;

public class OptionsRequest extends Request {

	public OptionsRequest( String resource ) {
		this( resource, VERSION );
	}

	public OptionsRequest( String resource, float version ) {
		super( Method.OPTIONS, resource, version );
	}

}
