package com.agri.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 问题实体
 */
@Data
@TableName("question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 提问者ID */
    private Long userId;

    /** 分类ID */
    private Long categoryId;

    /** 浏览量 */
    private Integer viewCount;

    /** 状态：1正常 0隐藏 */
    private Integer status;

    /** 是否已解决：1已解决 0未解决 */
    private Integer isResolved;

    /** 最佳答案ID */
    private Long bestAnswerId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @TableLogic
    private LocalDateTime deletedAt;
}
