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

		Headers headers = response.getHeaders();
		for( String key : headers.keySet() ) {
			for( String value : headers.getAll( key ) ) {
				writer.write( String.format( "%s: %s%s", key, value, LINE_SEPARATOR ) );
			}
		}

		writer.write( LINE_SEPARATOR );
		writer.write( response.getBody() );
		writer.flush();

	}

}
