package com.agri.backend.utils;

import java.util.regex.Pattern;

/**
 * XSS 清洗工具
 */
public class XssUtils {

    private static final Pattern SCRIPT_PATTERN =
            Pattern.compile("<\\s*script[^>]*>.*?<\\s*/\\s*script\\s*>",
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private static final Pattern IFRAME_PATTERN =
            Pattern.compile("<\\s*iframe[^>]*>.*?<\\s*/\\s*iframe\\s*>",
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private XssUtils() {
    }

    /**
     * 清洗 XSS 危险内容
     */
    public static String clean(String content) {
        if (content == null) {
            return null;
        }
        String result = SCRIPT_PATTERN.matcher(content).replaceAll("");
        result = IFRAME_PATTERN.matcher(result).replaceAll("");
        result = result.replaceAll("(?i)javascript:", "");
        return result;
    }
}
