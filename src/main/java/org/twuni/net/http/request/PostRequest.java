package org.twuni.net.http.request;

import org.twuni.net.http.Method;

public class PostRequest extends Request {

	public PostRequest( String resource, float version ) {
		super( Method.POST, resource, version );
	}

}
