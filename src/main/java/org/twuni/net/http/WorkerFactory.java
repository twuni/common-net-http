package org.twuni.net.http;

import java.net.Socket;

import org.twuni.common.Factory;

public class WorkerFactory implements Factory<Worker> {

	private final RequestReader reader = new RequestReader();
	private final ResponseWriter writer = new ResponseWriter();
	private final RequestHandler handler;

	public WorkerFactory( RequestHandler handler ) {
		this.handler = handler;
	}

	@Override
	public Worker createInstance( Object... args ) {

		if( args.length != 1 ) {
			throw new IllegalArgumentException();
		}

		return new Worker( (Socket) args[0], reader, handler, writer );

	}

}
