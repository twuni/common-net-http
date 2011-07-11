package org.twuni.net.http;

import org.twuni.common.Filter;
import org.twuni.net.http.responder.Responder;
import org.twuni.net.http.response.Response;
import org.twuni.net.http.response.filter.DateFilter;

public class Server extends org.twuni.net.Server {

	public Server( int port, Responder responder ) {
		this( port, responder, new DateFilter() );
	}

	public Server( int port, Responder responder, Filter<Response> filter ) {
		super( port, new WorkerFactory( responder, filter ) );
	}

}
