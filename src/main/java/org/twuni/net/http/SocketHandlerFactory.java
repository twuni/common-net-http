package org.twuni.net.http;

import java.net.Socket;

import org.twuni.net.Factory;

public class SocketHandlerFactory implements Factory<SocketHandler> {

	private final RequestReader reader = new RequestReader();
	private final ResponseWriter writer = new ResponseWriter();
	private final RequestHandler handler;

	public SocketHandlerFactory( RequestHandler handler ) {
		this.handler = handler;
	}

	@Override
	public SocketHandler createInstance( Object... args ) {

		if( args.length != 1 ) {
			throw new IllegalArgumentException();
		}

		return new SocketHandler( (Socket) args[0], reader, handler, writer );

	}

}
