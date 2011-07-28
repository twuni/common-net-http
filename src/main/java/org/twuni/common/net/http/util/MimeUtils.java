package org.twuni.common.net.http.util;

import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil2;

public class MimeUtils {

	private static final MimeType UNKNOWN_TYPE = new MimeType( "application/octet-stream" );
	private static final MimeUtil2 MIME = new MimeUtil2();

	static {
		MIME.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
	}

	public static String getContentType( byte [] buffer ) {
		return MIME.getMimeTypes( buffer, UNKNOWN_TYPE ).iterator().next().toString();
	}

}
