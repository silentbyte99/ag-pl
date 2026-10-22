package com.agri.backend.controller;

import com.agri.backend.common.Result;
import com.agri.backend.dto.QuestionDTO;
import com.agri.backend.entity.Answer;
import com.agri.backend.entity.Question;
import com.agri.backend.service.AnswerService;
import com.agri.backend.service.QuestionService;
import com.agri.backend.utils.SensitiveWordUtils;
import com.agri.backend.utils.UserContext;
import com.agri.backend.utils.XssUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 问题接口
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private AnswerService answerService;

    /**
     * 分页查询问题
     */
    @GetMapping("/page")
    public Result<IPage<Question>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String keyword) {
        return Result.success(questionService.getQuestionPage(page, pageSize, keyword));
    }

    /**
     * 新增问题（需登录）
     */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody QuestionDTO dto) {
        dto.setTitle(XssUtils.clean(SensitiveWordUtils.filter(dto.getTitle())));
        dto.setContent(XssUtils.clean(SensitiveWordUtils.filter(dto.getContent())));
        Long userId = UserContext.getUserId();
        questionService.addQuestion(dto, userId);
        return Result.success();
    }

    /**
     * 问题详情
     */
    @GetMapping("/{id}")
    public Result<Question> detail(@PathVariable Long id) {
        return Result.success(questionService.getById(id));
    }

    /**
     * 获取问题的所有回复
     */
    @GetMapping("/{id}/answers")
    public Result<List<Answer>> answers(@PathVariable Long id) {
        return Result.success(answerService.getAnswersByQuestionId(id));
    }

    /**
     * 采纳最佳答案（需登录，仅提问者）
     */
    @PostMapping("/accept")
    public Result<Void> accept(@RequestParam Long questionId, @RequestParam Long answerId) {
        Long currentUserId = UserContext.getUserId();
        answerService.acceptAnswer(questionId, answerId, currentUserId);
        return Result.success();
    }
}
