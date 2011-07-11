package org.twuni.net.http;

import java.net.Socket;

import org.twuni.common.Factory;
import org.twuni.common.Filter;
import org.twuni.net.http.responder.Responder;
import org.twuni.net.http.response.Response;

public class WorkerFactory implements Factory<Worker> {

	private final Responder responder;
	private final Filter<Response> filter;

	public WorkerFactory( Responder responder, Filter<Response> filter ) {
		this.responder = responder;
		this.filter = filter;
	}

	@Override
	public Worker createInstance( Object... args ) {

		if( args.length != 1 ) {
			throw new IllegalArgumentException();
		}

		return new Worker( (Socket) args[0], responder, filter );

	}

}
