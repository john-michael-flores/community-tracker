package com.membermanagement.Member.Management.common.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }
}