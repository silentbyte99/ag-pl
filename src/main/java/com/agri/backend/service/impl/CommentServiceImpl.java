package com.agri.backend.service.impl;

import com.agri.backend.common.BusinessException;
import com.agri.backend.dto.CommentDTO;
import com.agri.backend.entity.Comment;
import com.agri.backend.mapper.CommentMapper;
import com.agri.backend.service.CommentService;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论 Service 实现
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public void addComment(CommentDTO dto, Long userId) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(dto, comment);
        comment.setUserId(userId);
        comment.setStatus(1);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        save(comment);
    }

    @Override
    public void deleteComment(Long id, Long userId) {
        Comment comment = getById(id);
        if (comment == null) {
            throw new BusinessException("评论不存在");
        }
        if (!comment.getUserId().equals(userId)) {
            throw new BusinessException("只能删除自己的评论");
        }
        removeById(id);
    }

    @Override
    public List<Comment> getComments(Long targetId, String targetType) {
        return lambdaQuery()
                .eq(Comment::getTargetId, targetId)
                .eq(Comment::getTargetType, targetType)
                .eq(Comment::getStatus, 1)
                .orderByAsc(Comment::getCreatedAt)
                .list();
    }
}
