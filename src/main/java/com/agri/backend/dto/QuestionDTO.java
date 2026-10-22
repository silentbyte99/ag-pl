package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 问题 DTO
 */
@Data
public class QuestionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private Long categoryId;
}
