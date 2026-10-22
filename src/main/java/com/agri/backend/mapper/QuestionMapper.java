package com.agri.backend.mapper;

import com.agri.backend.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题 Mapper
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
