package org.twuni.common.net.http.response.filter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.twuni.common.Filter;
import org.twuni.common.net.http.Header;
import org.twuni.common.net.http.response.Response;

public class DateFilter implements Filter<Response> {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss z" );

	@Override
	public Response filter( Response response ) {
		response.getHeaders().put( Header.DATE, DATE_FORMAT.format( new Date() ) );
		return response;
	}

}
