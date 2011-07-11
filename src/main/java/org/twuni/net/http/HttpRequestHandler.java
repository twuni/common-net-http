package org.twuni.net.http;

import org.twuni.net.http.request.HttpRequest;
import org.twuni.net.http.response.HttpResponse;

public interface HttpRequestHandler {

	public HttpResponse respondTo( HttpRequest request );

}
