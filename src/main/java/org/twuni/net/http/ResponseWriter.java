package org.twuni.net.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import org.twuni.net.http.response.Response;

public class ResponseWriter {

	public void write( Response response, OutputStream to ) throws IOException {

		Writer writer = new PrintWriter( to );

		writer.write( String.format( "HTTP/%.1f %s\n", Float.valueOf( response.getVersion() ), response.getStatus() ) );

		for( String key : response.getHeaders().keySet() ) {
			for( String value : response.getHeaders().get( key ) ) {
				writer.write( String.format( "%s: %s\n", key, value ) );
			}
		}

		writer.flush();

	}

}
