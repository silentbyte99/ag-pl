package com.agri.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 百科实体
 */
@Data
@TableName("wiki")
public class Wiki implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 作物名称 */
    private String name;

    /** 分类ID */
    private Long categoryId;

    /** 封面图URL */
    private String cover;

    /** 百科详情 */
    private String content;

    /** 浏览量 */
    private Integer viewCount;

    /** 状态：1上架 0下架 */
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @TableLogic
    private LocalDateTime deletedAt;
}
