package org.twuni.net.http.exception;

import org.twuni.net.http.Method;

public class UnsupportedMethodException extends UnsupportedOperationException {

	private final Method method;

	public UnsupportedMethodException( Method method ) {
		super( String.format( "Method not supported: %s", method ) );
		this.method = method;
	}

	public Method getMethod() {
		return method;
	}

}
