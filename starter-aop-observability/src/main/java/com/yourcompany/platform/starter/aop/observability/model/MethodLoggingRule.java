package com.yourcompany.platform.starter.aop.observability.model;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class MethodLoggingRule {
    private String pattern;
    private boolean enabled = true;
    private Boolean logRequest;
    private Boolean logResponse;
    private Boolean logException;
    private Boolean logExecutionTime;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLogRequest() {
        return logRequest;
    }

    public void setLogRequest(final Boolean logRequest) {
        this.logRequest = logRequest;
    }

    public Boolean getLogResponse() {
        return logResponse;
    }

    public void setLogResponse(final Boolean logResponse) {
        this.logResponse = logResponse;
    }

    public Boolean getLogException() {
        return logException;
    }

    public void setLogException(final Boolean logException) {
        this.logException = logException;
    }

    public Boolean getLogExecutionTime() {
        return logExecutionTime;
    }

    public void setLogExecutionTime(final Boolean logExecutionTime) {
        this.logExecutionTime = logExecutionTime;
    }
}
