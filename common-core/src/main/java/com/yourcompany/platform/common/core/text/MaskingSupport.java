package com.yourcompany.platform.common.core.text;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class MaskingSupport {
    private MaskingSupport() {
    }

    public static String maskEmail(final String email) {
        if (StringSupport.isBlank(email) || !email.contains("@")) {
            return email;
        }

        final int atIndex = email.indexOf('@');
        final String localPart = email.substring(0, atIndex);
        final String domainPart = email.substring(atIndex);

        if (localPart.length() <= 2) {
            return "*".repeat(localPart.length()) + domainPart;
        }

        return localPart.charAt(0)
                + "*".repeat(localPart.length() - 2)
                + localPart.charAt(localPart.length() - 1)
                + domainPart;
    }

    public static String maskPhone(final String value) {
        return maskExceptLast(value, 4);
    }

    public static String maskDocument(final String value) {
        return maskExceptLast(value, 3);
    }

    public static String maskExceptLast(final String value, final int visibleLast) {
        if (StringSupport.isBlank(value)) {
            return value;
        }
        final String trimmed = value.trim();
        if (trimmed.length() <= visibleLast) {
            return "*".repeat(trimmed.length());
        }
        return "*".repeat(trimmed.length() - visibleLast) + trimmed.substring(trimmed.length() - visibleLast);
    }
}
