package com.agri.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 举报实体
 */
@Data
@TableName("report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 目标内容ID */
    private Long targetId;

    /** 目标类型：news/tech/wiki/question/answer */
    private String targetType;

    /** 用户ID */
    private Long userId;

    /** 举报理由 */
    private String reason;

    /** 状态：0待处理 1已处理 */
    private Integer status;

    private LocalDateTime createdAt;
}
