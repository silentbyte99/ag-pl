package com.agri.backend.service.impl;

import com.agri.backend.entity.Like;
import com.agri.backend.mapper.LikeMapper;
import com.agri.backend.service.LikeService;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 点赞 Service 实现
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

    @Override
    public boolean toggleLike(Long targetId, String targetType, Long userId) {
        Like exist = lambdaQuery()
                .eq(Like::getTargetId, targetId)
                .eq(Like::getTargetType, targetType)
                .eq(Like::getUserId, userId)
                .one();
        if (exist != null) {
            removeById(exist.getId());
            return false;
        }
        Like like = new Like();
        like.setTargetId(targetId);
        like.setTargetType(targetType);
        like.setUserId(userId);
        like.setCreatedAt(LocalDateTime.now());
        save(like);
        return true;
    }
}
