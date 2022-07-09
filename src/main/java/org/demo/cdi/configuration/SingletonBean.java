package org.demo.cdi.configuration;

import javax.enterprise.inject.Stereotype;
import javax.inject.Singleton;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Stereotype
@Singleton
@Target({ElementType.TYPE_USE, TYPE})
@Retention(RUNTIME)
public @interface SingletonBean {
}
