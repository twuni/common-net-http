package org.twuni.common.net.http;

import static org.twuni.common.net.http.Message.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import org.twuni.common.net.exception.ConnectionClosedException;
import org.twuni.common.net.http.response.Response;

final class ResponseWriter {

	public void write( Response response, OutputStream to ) throws IOException {

		Writer writer = new PrintWriter( to );

		writer.write( String.format( "HTTP/%.1f %s%s", Float.valueOf( response.getVersion() ), response.getStatus(), LINE_SEPARATOR ) );

		Headers headers = response.getHeaders();
		for( String key : headers.keySet() ) {
			for( String value : headers.getAll( key ) ) {
				writer.write( String.format( "%s: %s%s", key, value, LINE_SEPARATOR ) );
			}
		}

		writer.write( LINE_SEPARATOR );
		writer.flush();
		to.write( response.getContent() );
		to.flush();

		if( response.getHeaders().get( Header.CONNECTION ).equals( "close" ) ) {
			throw new ConnectionClosedException( "The connection was closed by the server." );
		}

	}

}
