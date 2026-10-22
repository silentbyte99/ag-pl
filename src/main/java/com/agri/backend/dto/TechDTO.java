package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 技术 DTO
 */
@Data
public class TechDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Long categoryId;
    private String cover;
    private String content;
    /** 1上架 0下架 */
    private Integer status;
}
