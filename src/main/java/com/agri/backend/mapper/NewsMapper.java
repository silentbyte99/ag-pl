package com.agri.backend.mapper;

import com.agri.backend.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资讯 Mapper
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {
}
