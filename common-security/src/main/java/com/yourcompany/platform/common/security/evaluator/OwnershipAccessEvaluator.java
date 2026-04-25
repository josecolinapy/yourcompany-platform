package com.yourcompany.platform.common.security.evaluator;

import com.yourcompany.platform.common.security.model.AuthenticatedUser;

/*
 * @author josec
 * @project yourcompany-platform
 */
public interface OwnershipAccessEvaluator {
    boolean hasOwnership(AuthenticatedUser user, String resource, String resourceId);
}
