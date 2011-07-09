package org.twuni.net.http;

import org.junit.Test;

public class HttpListenerTest {

	@Test
	public void testListenForOneMinute() throws InterruptedException {
		HttpListener listener = new HttpListener( 8080 );
		listener.start();
		Thread.sleep( 1000 * 60 );
	}

}
