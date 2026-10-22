package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 评论 DTO
 */
@Data
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long targetId;
    private String targetType;
    private String content;
    private Long parentId;
}
