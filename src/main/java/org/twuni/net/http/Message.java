package org.twuni.net.http;

public abstract class Message {

	public static final float VERSION = 1.1f;
	public static final String LINE_SEPARATOR = "\n";

	protected final float version;
	protected final Headers headers = new Headers();
	protected String body = "";

	protected Message() {
		this( VERSION );
	}

	protected Message( float version ) {
		this.version = version;
	}

	public String getBody() {
		return body;
	}

	public float getVersion() {
		return version;
	}

	public Headers getHeaders() {
		return headers;
	}

}
