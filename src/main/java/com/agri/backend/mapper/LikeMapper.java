package com.agri.backend.mapper;

import com.agri.backend.entity.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 点赞 Mapper
 */
@Mapper
public interface LikeMapper extends BaseMapper<Like> {
}
