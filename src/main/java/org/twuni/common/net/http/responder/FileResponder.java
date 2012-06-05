package org.twuni.common.net.http.responder;

import static org.twuni.common.net.http.util.MIMEUtils.getContentType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.twuni.common.net.http.request.Request;
import org.twuni.common.net.http.response.Response;
import org.twuni.common.net.http.response.Status;

public class FileResponder implements Responder {

	private final Logger log = LoggerFactory.getLogger( getClass() );

	private final File parent;

	public FileResponder( File parent ) {
		this.parent = parent;
	}

	@Override
	public Response respondTo( Request request ) {
		try {
			File file = new File( parent, request.getResource() );
			byte [] buffer = readFully( file );
			return new Response( Status.OK, getContentType( file.getName(), buffer ), buffer );
		} catch( FileNotFoundException exception ) {
			return new Response( Status.NOT_FOUND );
		} catch( IOException exception ) {
			log.warn( exception.toString() );
			return new Response( Status.INTERNAL_SERVER_ERROR );
		}
	}

	protected byte [] readFully( File file ) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream( file );
		byte [] buffer = new byte [(int) file.length()];
		for( int length = 0; length < buffer.length; length += in.read( buffer, length, buffer.length - length ) ) {
		}
		in.close();
		return buffer;
	}

}
