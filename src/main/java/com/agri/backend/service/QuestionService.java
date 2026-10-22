package com.agri.backend.service;

import com.agri.backend.dto.QuestionDTO;
import com.agri.backend.entity.Question;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * 问题 Service
 */
public interface QuestionService extends IService<Question> {

    /**
     * 分页查询正常状态的问题
     */
    IPage<Question> getQuestionPage(Integer page, Integer pageSize, String keyword);

    /**
     * 新增问题
     */
    void addQuestion(QuestionDTO dto, Long userId);
}
