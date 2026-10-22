package com.agri.backend.mapper;

import com.agri.backend.entity.Tech;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 技术 Mapper
 */
@Mapper
public interface TechMapper extends BaseMapper<Tech> {
}
