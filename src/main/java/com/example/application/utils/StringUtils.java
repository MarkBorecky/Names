package com.example.application.utils;

public class StringUtils {
    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty() && !str.isBlank() && !str.equals("-");
    }

    public static boolean isNotEmpty(Integer i) {
        return i != null && i != 0;
    }
}
