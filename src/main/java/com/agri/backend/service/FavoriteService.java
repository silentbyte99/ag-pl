package com.agri.backend.service;

import com.agri.backend.entity.Favorite;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * 收藏 Service
 */
public interface FavoriteService extends IService<Favorite> {

    /**
     * 收藏/取消收藏
     * @return true=已收藏 false=已取消
     */
    boolean toggleFavorite(Long targetId, String targetType, Long userId);
}
