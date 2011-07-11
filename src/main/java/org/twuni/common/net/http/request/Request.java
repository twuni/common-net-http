package org.twuni.common.net.http.request;

import org.twuni.common.net.http.Message;
import org.twuni.common.net.http.Method;

public abstract class Request extends Message {

	private final Method method;
	private final String resource;

	protected Request( Method method, String resource ) {
		super();
		this.method = method;
		this.resource = resource;
	}

	protected Request( Method method, String resource, float version ) {
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

	public void setBody( String body ) {
		this.content = body;
	}

	@Override
	public String toString() {
		return String.format( "%s: {method=%s, resource=%s, version=%s, headers=%s, body=%s}", getClass().getSimpleName(), method, resource, Float.toString( version ), headers, content );
	}

}
