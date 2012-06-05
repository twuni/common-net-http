package org.twuni.common.net.http;

import org.junit.Assert;
import org.junit.Test;

public class UrlEncodedParametersTest {

	@Test
	public void testSinglePairDecoding() {
		URLEncodedParameters parameters = new URLEncodedParameters( "test=12345" );
		Assert.assertEquals( "12345", parameters.get( "test" ) );
	}
	
	@Test
	public void testMultiPairDecoding() {
		URLEncodedParameters parameters = new URLEncodedParameters( "a=b&b=c" );
		Assert.assertEquals( "b", parameters.get( "a" ) );
		Assert.assertEquals( "c", parameters.get( "b" ) );
	}

	@Test
	public void testMultiValueKeyDecoding() {
		URLEncodedParameters parameters = new URLEncodedParameters( "a=b&a=c" );
		Assert.assertEquals( "b", parameters.get( "a" ) );
		Assert.assertArrayEquals( new String [] { "b", "c" }, parameters.getAll( "a" ).toArray() );
	}
	
	@Test
	public void testKeyWithEncodedCharacters() {
		URLEncodedParameters parameters = new URLEncodedParameters( "this+is+a+test%20=solid" );
		Assert.assertEquals( "solid", parameters.get( "this is a test " ) );
	}
	
	@Test
	public void testValueWithEncodedCharacters() {
		URLEncodedParameters parameters = new URLEncodedParameters( "test=aint+got+no%20style." );
		Assert.assertEquals( "aint got no style.", parameters.get( "test" ) );
	}

}
