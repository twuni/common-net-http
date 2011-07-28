package org.twuni.common.net.http.responder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.common.net.http.exception.UnsupportedMethodException;
import org.twuni.common.net.http.request.Request;
import org.twuni.common.net.http.response.Response;
import org.twuni.common.net.http.response.Status;

public class ExceptionHandler implements Responder {

	private final Logger log = LoggerFactory.getLogger( getClass() );
	private final Responder responder;

	public ExceptionHandler( Responder responder ) {
		this.responder = responder;
	}

	@Override
	public Response respondTo( Request request ) {

		try {
			return responder.respondTo( request );
		} catch( UnsupportedMethodException exception ) {
			return new Response( Status.METHOD_NOT_ALLOWED );
		} catch( IllegalArgumentException exception ) {
			return new Response( Status.BAD_REQUEST );
		} catch( NullPointerException exception ) {
			log.warn( exception.toString() );
			for( StackTraceElement element : exception.getStackTrace() ) {
				log.warn( String.format( " - in %s.%s:%s", element.getClassName(), element.getMethodName(), Integer.valueOf( element.getLineNumber() ) ) );
			}
			return new Response( Status.INTERNAL_SERVER_ERROR );
		} catch( RuntimeException exception ) {
			log.warn( String.format( "[%s] %s", exception.getClass().getName(), exception.toString() ) );
			return new Response( Status.INTERNAL_SERVER_ERROR );
		} catch( Exception exception ) {
			throw new RuntimeException( exception );
		}

	}

}
