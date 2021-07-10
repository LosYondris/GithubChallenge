package me.jansv.challenge.util;

import androidx.annotation.Nullable;

public final class Strings {
    public static boolean isNullOrEmpty(@Nullable String str) {
        return (null == str || str.isEmpty());
    }
}
