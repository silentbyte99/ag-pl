package com.agri.backend.mapper;

import com.agri.backend.entity.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 举报 Mapper
 */
@Mapper
public interface ReportMapper extends BaseMapper<Report> {
}
