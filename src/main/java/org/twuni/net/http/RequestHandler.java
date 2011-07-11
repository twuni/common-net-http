package org.twuni.net.http;

import org.twuni.net.http.request.Request;
import org.twuni.net.http.response.Response;

public interface RequestHandler {

	public Response respondTo( Request request );

}
