package com.agri.backend.service.impl;

import com.agri.backend.common.BusinessException;
import com.agri.backend.dto.AnswerDTO;
import com.agri.backend.entity.Answer;
import com.agri.backend.entity.Question;
import com.agri.backend.mapper.AnswerMapper;
import com.agri.backend.mapper.QuestionMapper;
import com.agri.backend.service.AnswerService;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 回答 Service 实现
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return lambdaQuery()
                .eq(Answer::getQuestionId, questionId)
                .eq(Answer::getStatus, 1)
                .orderByAsc(Answer::getCreatedAt)
                .list();
    }

    @Override
    public void addAnswer(AnswerDTO dto, Long userId) {
        if (questionMapper.selectById(dto.getQuestionId()) == null) {
            throw new BusinessException("问题不存在");
        }
        Answer answer = new Answer();
        BeanUtils.copyProperties(dto, answer);
        answer.setUserId(userId);
        answer.setStatus(1);
        answer.setCreatedAt(LocalDateTime.now());
        answer.setUpdatedAt(LocalDateTime.now());
        save(answer);
    }

    @Override
    public void acceptAnswer(Long questionId, Long answerId, Long currentUserId) {
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            throw new BusinessException("问题不存在");
        }
        if (!question.getUserId().equals(currentUserId)) {
            throw new BusinessException("只有提问者才能采纳答案");
        }
        Answer answer = getById(answerId);
        if (answer == null || !answer.getQuestionId().equals(questionId)) {
            throw new BusinessException("回答不属于该问题");
        }
        question.setBestAnswerId(answerId);
        question.setIsResolved(1);
        question.setUpdatedAt(LocalDateTime.now());
        questionMapper.updateById(question);
    }
}
