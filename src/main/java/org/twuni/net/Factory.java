package org.twuni.net;

public interface Factory<T> {

	public T createInstance( Object... args );

}
