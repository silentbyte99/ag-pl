package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 回答 DTO
 */
@Data
public class AnswerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long questionId;
    private String content;
}
