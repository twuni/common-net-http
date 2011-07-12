package org.twuni.common.net.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.twuni.common.net.http.Method;

@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
public @interface RespondsTo {

	public Method method() default Method.GET;

	public String value();

}
