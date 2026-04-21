package com.yourcompany.platform.common.aop.annotation;

/*
 * @author josec
 * @project yourcompany-platform
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresOwnership {
    String resource();

    String idExpression();
}
