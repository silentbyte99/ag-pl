package com.agri.backend.controller;

import com.agri.backend.common.Result;
import com.agri.backend.dto.AnswerDTO;
import com.agri.backend.service.AnswerService;
import com.agri.backend.utils.SensitiveWordUtils;
import com.agri.backend.utils.UserContext;
import com.agri.backend.utils.XssUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回答接口
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Resource
    private AnswerService answerService;

    /**
     * 新增回答（需登录）
     */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody AnswerDTO dto) {
        dto.setContent(XssUtils.clean(SensitiveWordUtils.filter(dto.getContent())));
        Long userId = UserContext.getUserId();
        answerService.addAnswer(dto, userId);
        return Result.success();
    }
}
