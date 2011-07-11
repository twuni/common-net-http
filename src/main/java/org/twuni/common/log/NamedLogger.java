package org.twuni.common.log;

import org.slf4j.Logger;

public class NamedLogger {

	private final Logger log;
	private final String name;

	public NamedLogger( Logger log, String name ) {
		this.log = log;
		this.name = name;
	}

	public void trace( String message ) {
		log.trace( format( message ) );
	}

	public void debug( String message ) {
		log.debug( format( message ) );
	}

	public void info( String message ) {
		log.info( format( message ) );
	}

	public void warn( String message ) {
		log.warn( format( message ) );
	}

	public void error( String message ) {
		log.error( format( message ) );
	}

	public void trace( String message, Throwable throwable ) {
		log.trace( format( message ), throwable );
	}

	public void debug( String message, Throwable throwable ) {
		log.debug( format( message ), throwable );
	}

	public void info( String message, Throwable throwable ) {
		log.info( format( message ), throwable );
	}

	public void warn( String message, Throwable throwable ) {
		log.warn( format( message ), throwable );
	}

	public void error( String message, Throwable throwable ) {
		log.error( format( message ), throwable );
	}

	private String format( String message ) {
		return String.format( "%s %s", name, message );
	}

}
