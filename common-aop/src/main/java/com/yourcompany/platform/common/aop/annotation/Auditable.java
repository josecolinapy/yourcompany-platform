package com.yourcompany.platform.common.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @author josec
 * @project yourcompany-platform
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {
    String action();

    String resource() default "";

    boolean includeArguments() default false;

    boolean includeResult() default false;
}
