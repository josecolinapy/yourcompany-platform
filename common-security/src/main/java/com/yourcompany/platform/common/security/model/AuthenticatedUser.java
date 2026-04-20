package com.yourcompany.platform.common.security.model;

import java.util.Set;

/*
 * @author josec
 * @project yourcompany-platform
 */public record AuthenticatedUser(
        String subject,
        String username,
        Set<String> roles,
        Set<String> permissions
) {
}
