package com.yourcompany.platform.common.core.decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class BigDecimalSupport {
    private BigDecimalSupport() {
    }

    public static BigDecimal zero() {
        return BigDecimal.ZERO;
    }

    public static BigDecimal nullSafe(final BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    public static BigDecimal scale(final BigDecimal value) {
        return scale(value, RoundingPolicySupport.DEFAULT_SCALE, RoundingPolicySupport.DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal scale(final BigDecimal value, final int scale, final RoundingMode roundingMode) {
        return nullSafe(value).setScale(scale, roundingMode);
    }

    public static BigDecimal safeAdd(final BigDecimal left, final BigDecimal right) {
        return nullSafe(left).add(nullSafe(right));
    }

    public static BigDecimal safeSubtract(final BigDecimal left, final BigDecimal right) {
        return nullSafe(left).subtract(nullSafe(right));
    }

    public static BigDecimal safeMultiply(final BigDecimal left, final BigDecimal right) {
        return nullSafe(left).multiply(nullSafe(right));
    }

    public static BigDecimal safeDivide(final BigDecimal left, final BigDecimal right) {
        return safeDivide(left, right, RoundingPolicySupport.DEFAULT_SCALE, RoundingPolicySupport.DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal safeDivide(final BigDecimal left, final BigDecimal right, final int scale, final RoundingMode roundingMode) {
        if (right == null || right.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.setScale(scale, roundingMode);
        }
        return nullSafe(left).divide(right, scale, roundingMode);
    }

    public static boolean isZero(final BigDecimal value) {
        return nullSafe(value).compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isPositive(final BigDecimal value) {
        return nullSafe(value).compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isNegative(final BigDecimal value) {
        return nullSafe(value).compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean greaterThan(final BigDecimal left, final BigDecimal right) {
        return nullSafe(left).compareTo(nullSafe(right)) > 0;
    }

    public static boolean lessThan(final BigDecimal left, final BigDecimal right) {
        return nullSafe(left).compareTo(nullSafe(right)) < 0;
    }
}
