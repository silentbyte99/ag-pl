package com.agri.backend.utils;

/**
 * 敏感词过滤工具
 */
public class SensitiveWordUtils {

    private static final String[] SENSITIVE_WORDS = {"傻逼", "垃圾", "违禁词"};

    private SensitiveWordUtils() {
    }

    /**
     * 过滤敏感词，替换为 ***
     */
    public static String filter(String content) {
        if (content == null) {
            return null;
        }
        String result = content;
        for (String word : SENSITIVE_WORDS) {
            result = result.replace(word, "***");
        }
        return result;
    }
}
