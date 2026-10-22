package com.agri.backend.service;

import com.agri.backend.dto.AnswerDTO;
import com.agri.backend.entity.Answer;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.List;

/**
 * 回答 Service
 */
public interface AnswerService extends IService<Answer> {

    /**
     * 查询问题下的所有正常回复（按创建时间升序）
     */
    List<Answer> getAnswersByQuestionId(Long questionId);

    /**
     * 新增回答
     */
    void addAnswer(AnswerDTO dto, Long userId);

    /**
     * 采纳最佳答案
     */
    void acceptAnswer(Long questionId, Long answerId, Long currentUserId);
}
