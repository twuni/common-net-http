package org.twuni.net.log;

import org.slf4j.Logger;

public class NamedLogger {

	private final Logger log;
	private final String name;

	public NamedLogger( Logger log, String name ) {
		this.log = log;
		this.name = name;
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

	private String format( String message ) {
		return String.format( "%s %s", name, message );
	}

}
