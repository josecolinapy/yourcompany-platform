package com.yourcompany.platform.common.aop.annotation;

import com.yourcompany.platform.common.aop.model.LogicalOperator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @author josec
 * @project yourcompany-platform
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {
    String[] value();

    LogicalOperator operator() default LogicalOperator.AND;
}
