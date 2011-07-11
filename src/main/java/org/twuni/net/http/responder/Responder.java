package org.twuni.net.http.responder;

import org.twuni.net.http.request.Request;
import org.twuni.net.http.response.Response;

public interface Responder {

	public Response respondTo( Request request );

}
