package com.agri.backend.controller;

import com.agri.backend.common.Result;
import com.agri.backend.dto.WikiDTO;
import com.agri.backend.entity.Wiki;
import com.agri.backend.service.WikiService;
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
 * 百科接口
 */
@RestController
@RequestMapping("/wiki")
public class WikiController {

    @Resource
    private WikiService wikiService;

    /**
     * 分页查询百科
     */
    @GetMapping("/page")
    public Result<IPage<Wiki>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Long categoryId,
            String keyword) {
        return Result.success(wikiService.getWikiPage(page, pageSize, categoryId, keyword));
    }

    /**
     * 新增百科（需登录）
     */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody WikiDTO dto) {
        wikiService.addWiki(dto);
        return Result.success();
    }

    /**
     * 百科详情
     */
    @GetMapping("/{id}")
    public Result<Wiki> detail(@PathVariable Long id) {
        return Result.success(wikiService.getById(id));
    }
}
