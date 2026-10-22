package com.agri.backend.controller;

import com.agri.backend.common.Result;
import com.agri.backend.dto.TechDTO;
import com.agri.backend.entity.Tech;
import com.agri.backend.service.TechService;
import com.agri.backend.utils.UserContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 技术接口
 */
@RestController
@RequestMapping("/tech")
public class TechController {

    @Resource
    private TechService techService;

    /**
     * 分页查询技术
     */
    @GetMapping("/page")
    public Result<IPage<Tech>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Long categoryId,
            String keyword) {
        return Result.success(techService.getTechPage(page, pageSize, categoryId, keyword));
    }

    /**
     * 新增技术（需登录）
     */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody TechDTO dto) {
        Long authorId = UserContext.getUserId();
        techService.addTech(dto, authorId);
        return Result.success();
    }

    /**
     * 技术详情
     */
    @GetMapping("/{id}")
    public Result<Tech> detail(@PathVariable Long id) {
        return Result.success(techService.getById(id));
    }
}
