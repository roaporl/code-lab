package com.lab.rpc.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Associate this annotation to thrift service implementation class or DAO interface, counter will ignore
 * exceptions defined in this annotation.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CounterIgnoreException {

  /**
   * The Class of exception to be ignored.
   */
  Class<?>[] value();
}
