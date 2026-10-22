package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 分类 DTO
 */
@Data
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String type;
    private Integer sort;
}
