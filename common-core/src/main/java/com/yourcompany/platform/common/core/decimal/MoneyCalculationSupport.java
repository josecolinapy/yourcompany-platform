package com.yourcompany.platform.common.core.decimal;

import java.math.BigDecimal;
import java.util.Collection;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class MoneyCalculationSupport {
    private MoneyCalculationSupport() {
    }

    public static BigDecimal sum(final Collection<BigDecimal> values) {
        return values.stream().map(BigDecimalSupport::nullSafe).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal prorate(final BigDecimal total, final BigDecimal ratio, final BigDecimal ratioBase) {
        if (ratioBase == null || ratioBase.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return BigDecimalSupport.scale(
                BigDecimalSupport.safeMultiply(
                        total,
                        BigDecimalSupport.safeDivide(
                                ratio,
                                ratioBase,
                                RoundingPolicySupport.HIGH_PRECISION_SCALE,
                                RoundingPolicySupport.DEFAULT_ROUNDING_MODE
                        )
                )
        );
    }
}
