package com.yourcompany.platform.common.security.evaluator;

import com.yourcompany.platform.common.security.model.AuthenticatedUser;

/*
 * @author josec
 * @project yourcompany-platform
 */
public interface TenantAccessEvaluator {
    boolean hasAccess(AuthenticatedUser user, String tenantId);

}
