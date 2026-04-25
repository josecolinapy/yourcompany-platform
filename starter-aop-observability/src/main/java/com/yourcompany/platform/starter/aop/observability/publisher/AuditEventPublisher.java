package com.yourcompany.platform.starter.aop.observability.publisher;

/*
 * @author josec
 * @project yourcompany-platform
 */
public interface AuditEventPublisher {
    void publish(AuditEvent event);
}
