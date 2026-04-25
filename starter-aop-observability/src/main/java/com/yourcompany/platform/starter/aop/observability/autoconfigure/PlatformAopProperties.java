package com.yourcompany.platform.starter.aop.observability.autoconfigure;

import com.yourcompany.platform.common.aop.model.LogLevel;
import com.yourcompany.platform.starter.aop.observability.model.MethodLoggingRule;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/*
 * @author josec
 * @project yourcompany-platform
 */
@ConfigurationProperties(prefix = "platform.aop")
public class PlatformAopProperties {
    private boolean enabled = true;
    private boolean metricsEnabled = true;
    private boolean auditEnabled = true;
    private boolean traceEnabled = true;
    private final Logging logging = new Logging();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isMetricsEnabled() {
        return metricsEnabled;
    }

    public void setMetricsEnabled(final boolean metricsEnabled) {
        this.metricsEnabled = metricsEnabled;
    }

    public boolean isAuditEnabled() {
        return auditEnabled;
    }

    public void setAuditEnabled(final boolean auditEnabled) {
        this.auditEnabled = auditEnabled;
    }

    public boolean isTraceEnabled() {
        return traceEnabled;
    }

    public void setTraceEnabled(final boolean traceEnabled) {
        this.traceEnabled = traceEnabled;
    }

    public Logging getLogging() {
        return logging;
    }

    public static class Logging {

        private boolean enabled = true;
        private boolean logRequest = true;
        private boolean logResponse = true;
        private boolean logException = true;
        private boolean logExecutionTime = true;
        private boolean maskSensitiveData = true;
        private int maxPayloadLength = 2000;
        private boolean includeVoidResponse = false;
        private boolean includeArgumentTypes = true;
        private boolean includeMethodSignature = true;
        private LogLevel level = LogLevel.INFO;
        private List<String> excludedArgumentTypes = new ArrayList<>(List.of(
                "jakarta.servlet.http.HttpServletRequest",
                "jakarta.servlet.http.HttpServletResponse",
                "org.springframework.web.multipart.MultipartFile",
                "org.springframework.validation.BindingResult"
        ));
        private List<String> sensitiveFieldNames = new ArrayList<>(List.of(
                "password",
                "token",
                "authorization",
                "secret",
                "privateKey"
        ));
        private List<MethodLoggingRule> rules = new ArrayList<>();

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(final boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isLogRequest() {
            return logRequest;
        }

        public void setLogRequest(final boolean logRequest) {
            this.logRequest = logRequest;
        }

        public boolean isLogResponse() {
            return logResponse;
        }

        public void setLogResponse(final boolean logResponse) {
            this.logResponse = logResponse;
        }

        public boolean isLogException() {
            return logException;
        }

        public void setLogException(final boolean logException) {
            this.logException = logException;
        }

        public boolean isLogExecutionTime() {
            return logExecutionTime;
        }

        public void setLogExecutionTime(final boolean logExecutionTime) {
            this.logExecutionTime = logExecutionTime;
        }

        public boolean isMaskSensitiveData() {
            return maskSensitiveData;
        }

        public void setMaskSensitiveData(final boolean maskSensitiveData) {
            this.maskSensitiveData = maskSensitiveData;
        }

        public int getMaxPayloadLength() {
            return maxPayloadLength;
        }

        public void setMaxPayloadLength(final int maxPayloadLength) {
            this.maxPayloadLength = maxPayloadLength;
        }

        public boolean isIncludeVoidResponse() {
            return includeVoidResponse;
        }

        public void setIncludeVoidResponse(final boolean includeVoidResponse) {
            this.includeVoidResponse = includeVoidResponse;
        }

        public boolean isIncludeArgumentTypes() {
            return includeArgumentTypes;
        }

        public void setIncludeArgumentTypes(final boolean includeArgumentTypes) {
            this.includeArgumentTypes = includeArgumentTypes;
        }

        public boolean isIncludeMethodSignature() {
            return includeMethodSignature;
        }

        public void setIncludeMethodSignature(final boolean includeMethodSignature) {
            this.includeMethodSignature = includeMethodSignature;
        }

        public LogLevel getLevel() {
            return level;
        }

        public void setLevel(final LogLevel level) {
            this.level = level;
        }

        public List<String> getExcludedArgumentTypes() {
            return excludedArgumentTypes;
        }

        public void setExcludedArgumentTypes(final List<String> excludedArgumentTypes) {
            this.excludedArgumentTypes = excludedArgumentTypes;
        }

        public List<String> getSensitiveFieldNames() {
            return sensitiveFieldNames;
        }

        public void setSensitiveFieldNames(final List<String> sensitiveFieldNames) {
            this.sensitiveFieldNames = sensitiveFieldNames;
        }

        public List<MethodLoggingRule> getRules() {
            return rules;
        }

        public void setRules(final List<MethodLoggingRule> rules) {
            this.rules = rules;
        }
    }
}
