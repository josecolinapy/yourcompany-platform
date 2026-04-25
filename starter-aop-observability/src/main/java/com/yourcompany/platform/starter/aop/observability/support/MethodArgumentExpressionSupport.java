package com.yourcompany.platform.starter.aop.observability.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class MethodArgumentExpressionSupport {
    private final ExpressionParser expressionParser = new SpelExpressionParser();
    private final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    public String evaluateToString(final ProceedingJoinPoint joinPoint, final String expression) {
        if (expression == null || expression.isBlank()) {
            return null;
        }

        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String[] parameterNames = parameterNameDiscoverer.getParameterNames(methodSignature.getMethod());
        final Object[] args = joinPoint.getArgs();
        final StandardEvaluationContext context = new StandardEvaluationContext();

        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }

        final Object result = expressionParser.parseExpression(expression).getValue(context);
        return result == null ? null : String.valueOf(result);
    }
}
