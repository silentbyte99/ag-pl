package com.agri.backend.service.impl;

import com.agri.backend.entity.Favorite;
import com.agri.backend.mapper.FavoriteMapper;
import com.agri.backend.service.FavoriteService;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 收藏 Service 实现
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {

    @Override
    public boolean toggleFavorite(Long targetId, String targetType, Long userId) {
        Favorite exist = lambdaQuery()
                .eq(Favorite::getTargetId, targetId)
                .eq(Favorite::getTargetType, targetType)
                .eq(Favorite::getUserId, userId)
                .one();
        if (exist != null) {
            removeById(exist.getId());
            return false;
        }
        Favorite favorite = new Favorite();
        favorite.setTargetId(targetId);
        favorite.setTargetType(targetType);
        favorite.setUserId(userId);
        favorite.setCreatedAt(LocalDateTime.now());
        save(favorite);
        return true;
    }
}
