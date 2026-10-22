package com.agri.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 举报 DTO
 */
@Data
public class ReportDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long targetId;
    private String targetType;
    private String reason;
}
