package com.yourcompany.platform.common.core.decimal;

import java.math.RoundingMode;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class RoundingPolicySupport {
    public static final int DEFAULT_SCALE = 2;
    public static final int HIGH_PRECISION_SCALE = 8;
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    private RoundingPolicySupport() {
    }
}
