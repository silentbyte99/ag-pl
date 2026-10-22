package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 百科 DTO
 */
@Data
public class WikiDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /** 作物名称 */
    private String name;
    private Long categoryId;
    private String cover;
    /** 百科详情 */
    private String content;
    /** 1上架 0下架 */
    private Integer status;
}
