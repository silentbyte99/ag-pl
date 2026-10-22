package com.agri.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收藏实体
 */
@Data
@TableName("favorite")
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 目标内容ID */
    private Long targetId;

    /** 目标类型：news/tech/wiki/question/answer */
    private String targetType;

    /** 用户ID */
    private Long userId;

    private LocalDateTime createdAt;
}
