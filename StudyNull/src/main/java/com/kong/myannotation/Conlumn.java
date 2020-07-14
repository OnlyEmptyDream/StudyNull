package com.kong.myannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Conlumn {
    Class<?> type();
    String comment();
    int lenth() default 0;
    boolean index() default false;
    String defaults() default "";
}
