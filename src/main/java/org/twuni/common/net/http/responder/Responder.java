package org.twuni.common.net.http.responder;

import org.twuni.common.net.http.request.Request;
import org.twuni.common.net.http.response.Response;

public interface Responder {

	public Response respondTo( Request request );

}
