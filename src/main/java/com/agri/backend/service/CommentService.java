package com.agri.backend.service;

import com.agri.backend.dto.CommentDTO;
import com.agri.backend.entity.Comment;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.List;

/**
 * 评论 Service
 */
public interface CommentService extends IService<Comment> {

    /**
     * 新增评论
     */
    void addComment(CommentDTO dto, Long userId);

    /**
     * 删除评论（只能删除自己的）
     */
    void deleteComment(Long id, Long userId);

    /**
     * 查询某内容下的评论列表（按创建时间升序）
     */
    List<Comment> getComments(Long targetId, String targetType);
}
