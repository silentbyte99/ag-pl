package com.agri.backend.service;

import com.agri.backend.dto.ReportDTO;
import com.agri.backend.entity.Report;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * 举报 Service
 */
public interface ReportService extends IService<Report> {

    /**
     * 新增举报
     */
    void addReport(ReportDTO dto, Long userId);
}
