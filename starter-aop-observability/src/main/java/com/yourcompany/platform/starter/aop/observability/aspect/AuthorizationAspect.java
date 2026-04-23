package com.yourcompany.platform.starter.aop.observability.aspect;

import com.yourcompany.platform.common.aop.annotation.RequiresOwnership;
import com.yourcompany.platform.common.aop.annotation.RequiresPermission;
import com.yourcompany.platform.common.aop.annotation.RequiresRole;
import com.yourcompany.platform.common.aop.annotation.RequiresTenantAccess;
import com.yourcompany.platform.common.aop.model.LogicalOperator;
import com.yourcompany.platform.common.security.evaluator.OwnershipAccessEvaluator;
import com.yourcompany.platform.common.security.evaluator.TenantAccessEvaluator;
import com.yourcompany.platform.common.security.model.AuthenticatedUser;
import com.yourcompany.platform.common.security.support.SecurityContextFacade;
import com.yourcompany.platform.starter.aop.observability.support.MethodArgumentExpressionSupport;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/*
 * @author josec
 * @project yourcompany-platform
 */
@Aspect
public class AuthorizationAspect {
    private final SecurityContextFacade securityContextFacade;
    private final Optional<TenantAccessEvaluator> tenantAccessEvaluator;
    private final Optional<OwnershipAccessEvaluator> ownershipAccessEvaluator;
    private final MethodArgumentExpressionSupport methodArgumentExpressionSupport;

    public AuthorizationAspect(
            final SecurityContextFacade securityContextFacade,
            final Optional<TenantAccessEvaluator> tenantAccessEvaluator,
            final Optional<OwnershipAccessEvaluator> ownershipAccessEvaluator,
            final MethodArgumentExpressionSupport methodArgumentExpressionSupport
    ) {
        this.securityContextFacade = securityContextFacade;
        this.tenantAccessEvaluator = tenantAccessEvaluator;
        this.ownershipAccessEvaluator = ownershipAccessEvaluator;
        this.methodArgumentExpressionSupport = methodArgumentExpressionSupport;
    }

    @Around("@annotation(requiresPermission) || @within(requiresPermission)")
    public Object checkPermission(final ProceedingJoinPoint joinPoint, final RequiresPermission requiresPermission) throws Throwable {
        final AuthenticatedUser user = getCurrentUser();
        if (!matches(user.permissions(), requiresPermission.value(), requiresPermission.operator())) {
            throw new AccessDeniedException("Permission denied");
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(requiresRole) || @within(requiresRole)")
    public Object checkRole(final ProceedingJoinPoint joinPoint, final RequiresRole requiresRole) throws Throwable {
        final AuthenticatedUser user = getCurrentUser();
        if (!matches(user.roles(), requiresRole.value(), requiresRole.operator())) {
            throw new AccessDeniedException("Role denied");
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(requiresTenantAccess) || @within(requiresTenantAccess)")
    public Object checkTenantAccess(final ProceedingJoinPoint joinPoint, final RequiresTenantAccess requiresTenantAccess) throws Throwable {
        final AuthenticatedUser user = getCurrentUser();
        final String tenantId = methodArgumentExpressionSupport.evaluateToString(joinPoint, requiresTenantAccess.tenantIdExpression());
        if (tenantAccessEvaluator.isEmpty() || !tenantAccessEvaluator.get().hasAccess(user, tenantId)) {
            throw new AccessDeniedException("Tenant access denied");
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(requiresOwnership) || @within(requiresOwnership)")
    public Object checkOwnership(final ProceedingJoinPoint joinPoint, final RequiresOwnership requiresOwnership) throws Throwable {
        final AuthenticatedUser user = getCurrentUser();
        final String resourceId = methodArgumentExpressionSupport.evaluateToString(joinPoint, requiresOwnership.idExpression());
        if (ownershipAccessEvaluator.isEmpty() || !ownershipAccessEvaluator.get().hasOwnership(user, requiresOwnership.resource(), resourceId)) {
            throw new AccessDeniedException("Ownership access denied");
        }
        return joinPoint.proceed();
    }

    private AuthenticatedUser getCurrentUser() throws AccessDeniedException {
        return securityContextFacade.getCurrentUser().orElseThrow(() -> new AccessDeniedException("Unauthenticated access"));
    }

    private boolean matches(final Set<String> actualValues, final String[] requiredValues, final LogicalOperator operator) {
        if (requiredValues == null || requiredValues.length == 0) {
            return true;
        }

        return operator == LogicalOperator.AND
                ? Arrays.stream(requiredValues).allMatch(actualValues::contains)
                : Arrays.stream(requiredValues).anyMatch(actualValues::contains);
    }
}
