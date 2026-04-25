package com.yourcompany.platform.starter.aop.observability.publisher;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class NoOpAuditEventPublisher implements AuditEventPublisher {
    @Override
    public void publish(final AuditEvent event) {
        // No-op
    }
}
