package org.twuni.net.http;

import static org.twuni.net.http.Message.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import org.twuni.net.http.response.Response;

public class ResponseWriter {

	public void write( Response response, OutputStream to ) throws IOException {

		Writer writer = new PrintWriter( to );

		writer.write( String.format( "HTTP/%.1f %s%s", Float.valueOf( response.getVersion() ), response.getStatus(), LINE_SEPARATOR ) );

		for( String key : response.getHeaders().keySet() ) {
			for( String value : response.getHeaders().get( key ) ) {
				writer.write( String.format( "%s: %s%s", key, value, LINE_SEPARATOR ) );
			}
		}

		writer.write( LINE_SEPARATOR );
		writer.write( response.getBody() );
		writer.flush();

	}

}
