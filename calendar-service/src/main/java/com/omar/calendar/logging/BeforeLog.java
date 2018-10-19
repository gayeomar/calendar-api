package com.omar.calendar.logging;

/**
 * 
 * @author Omar.Gaye
 *
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeLog {
    boolean enable() default true;
}
