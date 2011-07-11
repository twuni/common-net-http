package org.twuni.common.net.http;

public abstract class Message {

	public static final float VERSION = 1.1f;
	public static final String LINE_SEPARATOR = "\n";

	protected final float version;
	protected final Headers headers = new Headers();
	protected String content = "";

	protected Message() {
		this( VERSION );
	}

	protected Message( float version ) {
		this.version = version;
	}

	public String getContent() {
		return content;
	}

	public float getVersion() {
		return version;
	}

	public Headers getHeaders() {
		return headers;
	}

}
