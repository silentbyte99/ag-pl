package com.agri.backend.service;

import com.agri.backend.entity.Like;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * 点赞 Service
 */
public interface LikeService extends IService<Like> {

    /**
     * 点赞/取消点赞
     * @return true=已点赞 false=已取消
     */
    boolean toggleLike(Long targetId, String targetType, Long userId);
}
