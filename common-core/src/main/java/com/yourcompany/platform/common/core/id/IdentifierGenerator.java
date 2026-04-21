package com.yourcompany.platform.common.core.id;

import java.util.UUID;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class IdentifierGenerator {
    private IdentifierGenerator() {
    }

    public static UUID newUuid() {
        return UUID.randomUUID();
    }

    public static String newUuidString() {
        return UUID.randomUUID().toString();
    }
}
