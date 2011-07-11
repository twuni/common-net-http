package org.twuni.net.http;

import java.net.Socket;

import org.twuni.common.Factory;
import org.twuni.common.Filter;
import org.twuni.net.http.responder.Responder;
import org.twuni.net.http.response.Response;

public class WorkerFactory implements Factory<Worker> {

	private final RequestReader reader = new RequestReader();
	private final Responder handler;
	private final Filter<Response> filter;
	private final ResponseWriter writer = new ResponseWriter();

	public WorkerFactory( Responder handler, Filter<Response> filter ) {
		this.handler = handler;
		this.filter = filter;
	}

	@Override
	public Worker createInstance( Object... args ) {

		if( args.length != 1 ) {
			throw new IllegalArgumentException();
		}

		return new Worker( (Socket) args[0], reader, handler, writer, filter );

	}

}
