package com.agri.backend.utils;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 浏览量控制工具（基于 Redis 去重）
 */
@Component
public class ViewCountUtils {

    private static final Logger log = LoggerFactory.getLogger(ViewCountUtils.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 检查并记录一次浏览
     *
     * @param targetType 目标类型：news/tech/wiki/question
     * @param targetId   目标ID
     * @param identifier 访客标识（IP 或 userId）
     * @return true=有效浏览（30 分钟内首次） false=重复浏览或 Redis 不可用
     */
    public boolean checkAndAddView(String targetType, Long targetId, String identifier) {
        try {
            String key = "view:" + targetType + ":" + targetId + ":" + identifier;
            Boolean set = stringRedisTemplate.opsForValue()
                    .setIfAbsent(key, "1", 30, TimeUnit.MINUTES);
            if (Boolean.TRUE.equals(set)) {
                stringRedisTemplate.opsForHash()
                        .increment("view_count:" + targetType, String.valueOf(targetId), 1);
                return true;
            }
        } catch (Exception e) {
            log.warn("Redis 浏览量记录失败，跳过计数: {}", e.getMessage());
        }
        return false;
    }

    /**
     * 获取浏览量
     */
    public Object getViewCount(String targetType, Long targetId) {
        try {
            return stringRedisTemplate.opsForHash()
                    .get("view_count:" + targetType, String.valueOf(targetId));
        } catch (Exception e) {
            log.warn("Redis 浏览量查询失败: {}", e.getMessage());
            return null;
        }
    }
}
