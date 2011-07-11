package org.twuni.net.http;

/**
 * HTTP 1.1 request, response, and entity headers.
 * 
 * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html#sec5.3">RFC-2616, section 5.3</a>
 * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec6.html#sec6.2">RFC-2616, section 6.2</a>
 * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec7.html#sec7.1">RFC-2616, section 7.1</a>
 */
public enum Header {

	ACCEPT( "Accept" ),
	ACCEPT_CHARSET( "Accept-Charset" ),
	ACCEPT_ENCODING( "Accept-Encoding" ),
	ACCEPT_LANGUAGE( "Accept-Language" ),
	ACCEPT_RANGES( "Accept-Ranges" ),
	AGE( "Age" ),
	ALLOW( "Allow" ),
	AUTHORIZATION( "Authorization" ),
	CACHE_CONTROL( "Cache-Control" ),
	CONNECTION( "Connection" ),
	CONTENT_ENCODING( "Content-Encoding" ),
	CONTENT_LANGUAGE( "Content-Language" ),
	CONTENT_LENGTH( "Content-Length" ),
	CONTENT_LOCATION( "Content-Location" ),
	CONTENT_MD5( "Content-MD5" ),
	CONTENT_RANGE( "Content-Range" ),
	CONTENT_TYPE( "Content-Type" ),
	DATE( "Date" ),
	ETAG( "ETag" ),
	EXPECT( "Expect" ),
	EXPIRES( "Expires" ),
	FROM( "From" ),
	HOST( "Host" ),
	IF_MATCH( "If-Match" ),
	IF_MODIFIED_SINCE( "If-Modified-Since" ),
	IF_NONE_MATCH( "If-None-Match" ),
	IF_RANGE( "If-Range" ),
	IF_UNMODIFIED_SINCE( "If-Unmodified-Since" ),
	LAST_MODIFIED( "Last-Modified" ),
	LOCATION( "Location" ),
	MAX_FORWARDS( "Max-Forwards" ),
	PRAGMA( "Pragma" ),
	PROXY_AUTHENTICATE( "Proxy-Authenticate" ),
	PROXY_AUTHORIZATION( "Proxy-Authorization" ),
	RANGE( "Range" ),
	REFERER( "Referer" ),
	RETRY_AFTER( "Retry-After" ),
	SERVER( "Server" ),
	TE( "TE" ),
	TRAILER( "Trailer" ),
	TRANSFER_ENCODING( "Transfer-Encoding" ),
	UPGRADE( "Upgrade" ),
	USER_AGENT( "User-Agent" ),
	VARY( "Vary" ),
	VIA( "Via" ),
	WARNING( "Warning" ),
	WWW_AUTHENTICATE( "WWW-Authenticate" );

	private final String name;

	private Header( String name ) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
