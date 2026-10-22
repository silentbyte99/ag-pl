package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 资讯 DTO
 */
@Data
public class NewsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Long categoryId;
    private String cover;
    private String content;
    /** 1上架 0下架 */
    private Integer status;
    /** 1置顶 0普通 */
    private Integer isTop;
}
