package org.twuni.common.net.http.request;

import org.twuni.common.net.http.Message;
import org.twuni.common.net.http.Method;

public class Request extends Message {

	private final Method method;
	private final String resource;

	public Request( Method method, String resource ) {
		super();
		this.method = method;
		this.resource = resource;
	}

	public Request( Method method, String resource, float version ) {
		super( version );
		this.method = method;
		this.resource = resource;
	}

	public Method getMethod() {
		return method;
	}

	public String getResource() {
		return resource;
	}

	public void setContent( byte [] content ) {
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format( "%s: {method=%s, resource=%s, version=%s, headers=%s, content=%s}", getClass().getSimpleName(), method, resource, Float.toString( version ), headers, new String( content ) );
	}

}
