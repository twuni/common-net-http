package org.twuni.common.net.http.util;

import java.util.Collection;

import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil2;

public class MIMEUtils {

	private static final MimeType UNKNOWN_TYPE = new MimeType( "application/octet-stream" );
	private static final MimeUtil2 MIME = new MimeUtil2();

	static {
		MIME.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
		MIME.registerMimeDetector( "eu.medsea.mimeutil.detector.ExtensionMimeDetector" );
	}

	public static String getContentType( String filename, byte [] buffer ) {
		Collection<?> types = MIME.getMimeTypes( buffer, UNKNOWN_TYPE );
		if( UNKNOWN_TYPE.equals( types.iterator().next() ) ) {
			types = MIME.getMimeTypes( filename, UNKNOWN_TYPE );
		}
		return types.iterator().next().toString();
	}

	public static String getContentType( String filename ) {
		return MIME.getMimeTypes( filename, UNKNOWN_TYPE ).iterator().next().toString();
	}

	public static String getContentType( byte [] buffer ) {
		return MIME.getMimeTypes( buffer, UNKNOWN_TYPE ).iterator().next().toString();
	}

}
