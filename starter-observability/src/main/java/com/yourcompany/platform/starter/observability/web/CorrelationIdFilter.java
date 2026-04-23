package com.yourcompany.platform.starter.observability.web;

import com.yourcompany.platform.common.logging.constants.LoggingConstants;
import com.yourcompany.platform.common.logging.model.CorrelationContext;
import com.yourcompany.platform.common.logging.support.MdcSupport;
import com.yourcompany.platform.starter.observability.autoconfigure.PlatformObservabilityProperties;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class CorrelationIdFilter extends OncePerRequestFilter {
    private final PlatformObservabilityProperties properties;

    public CorrelationIdFilter(final PlatformObservabilityProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        final String correlationId = resolveCorrelationId(request);
        response.setHeader(properties.getCorrelationIdHeader(), correlationId);
        request.setAttribute(LoggingConstants.CORRELATION_ID, correlationId);

        if (properties.isIncludeMdc()) {
            MdcSupport.putCorrelationContext(new CorrelationContext(correlationId, null));
            MdcSupport.put(LoggingConstants.REQUEST_METHOD, request.getMethod());
            MdcSupport.put(LoggingConstants.REQUEST_URI, request.getRequestURI());
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            MdcSupport.clear();
        }
    }

    private String resolveCorrelationId(final HttpServletRequest request) {
        final String headerName = properties.getCorrelationIdHeader();
        final String headerValue = request.getHeader(headerName);
        return (headerValue == null || headerValue.isBlank()) ? UUID.randomUUID().toString() : headerValue;
    }
}
