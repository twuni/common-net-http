package org.twuni.net.http;

public class Entity {

	private final String type;
	private final String content;

	public Entity( String type, String content ) {
		this.type = type;
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public int getContentLength() {
		return content.getBytes().length;
	}

}
