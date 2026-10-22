package com.agri.backend.service.impl;

import com.agri.backend.dto.ReportDTO;
import com.agri.backend.entity.Report;
import com.agri.backend.mapper.ReportMapper;
import com.agri.backend.service.ReportService;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 举报 Service 实现
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Override
    public void addReport(ReportDTO dto, Long userId) {
        Report report = new Report();
        BeanUtils.copyProperties(dto, report);
        report.setUserId(userId);
        report.setStatus(0);
        report.setCreatedAt(LocalDateTime.now());
        save(report);
    }
}
