package org.twuni.common;

public interface Factory<T> {

	public T createInstance( Object... args );

}
