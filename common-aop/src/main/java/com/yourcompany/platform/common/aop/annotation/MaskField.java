package com.yourcompany.platform.common.aop.annotation;

import com.yourcompany.platform.common.aop.model.MaskStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @author josec
 * @project yourcompany-platform
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskField {
    MaskStrategy strategy() default MaskStrategy.FULL;
}
