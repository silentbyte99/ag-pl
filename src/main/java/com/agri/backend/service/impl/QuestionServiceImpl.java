package com.agri.backend.service.impl;

import com.agri.backend.dto.QuestionDTO;
import com.agri.backend.entity.Question;
import com.agri.backend.mapper.QuestionMapper;
import com.agri.backend.service.QuestionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 问题 Service 实现
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Override
    public IPage<Question> getQuestionPage(Integer page, Integer pageSize, String keyword) {
        return lambdaQuery()
                .eq(Question::getStatus, 1)
                .like(keyword != null && !keyword.trim().isEmpty(), Question::getTitle, keyword)
                .orderByDesc(Question::getCreatedAt)
                .page(new Page<>(page, pageSize));
    }

    @Override
    public void addQuestion(QuestionDTO dto, Long userId) {
        Question question = new Question();
        BeanUtils.copyProperties(dto, question);
        question.setUserId(userId);
        question.setViewCount(0);
        question.setIsResolved(0);
        question.setStatus(1);
        question.setCreatedAt(LocalDateTime.now());
        question.setUpdatedAt(LocalDateTime.now());
        save(question);
    }
}
