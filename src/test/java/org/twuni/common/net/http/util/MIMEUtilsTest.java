package org.twuni.common.net.http.util;

import org.junit.Assert;
import org.junit.Test;
import org.twuni.common.util.Base64;

public class MIMEUtilsTest {

	@Test
	public void testGetTypeByFilenameWithCssExtension() {
		String filename = "about.css";
		String expected = "text/css";
		String actual = MIMEUtils.getContentType( filename );
		Assert.assertEquals( expected, actual );
	}

	@Test
	public void testGetTypeByContentForObviousHtml() {
		byte [] content = "<html>\n<head>\n<title>Webpage</title>\n</head>\n<body>\nHello, world!\n</body>\n</html>\n".getBytes();
		String expected = "text/html";
		String actual = MIMEUtils.getContentType( content );
		Assert.assertEquals( expected, actual );
	}

	@Test
	public void testGetTypeByFilenameAndContentForWeirdContentButObviousFilename() {
		String filename = "about.css";
		byte [] content = Base64.decode( "12345Abcd12340abfha38afl98SAFNZofeu=" );
		String expected = "text/css";
		Assert.assertEquals( expected, MIMEUtils.getContentType( filename, content ) );
	}

}
