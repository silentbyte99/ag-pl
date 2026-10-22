package com.agri.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 技术实体
 */
@Data
@TableName("tech")
public class Tech implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 标题 */
    private String title;

    /** 分类ID */
    private Long categoryId;

    /** 封面图URL */
    private String cover;

    /** 内容 */
    private String content;

    /** 作者ID */
    private Long authorId;

    /** 浏览量 */
    private Integer viewCount;

    /** 状态：1上架 0下架 */
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @TableLogic
    private LocalDateTime deletedAt;
}
