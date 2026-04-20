package com.yourcompany.platform.common.security.support;

import com.yourcompany.platform.common.security.model.AuthenticatedUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class SecurityContextFacade {
    public Optional<AuthenticatedUser> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        final String subject = authentication.getName();
        authentication.getAuthorities();
        final Set<String> authorities = authentication.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toSet());

        return Optional.of(new AuthenticatedUser(subject, subject, authorities, authorities));
    }

    public Optional<String> getCurrentSubject() {
        return getCurrentUser().map(AuthenticatedUser::subject);
    }
}
