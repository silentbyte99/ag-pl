package com.agri.backend.controller;

import com.agri.backend.common.Result;
import com.agri.backend.dto.CategoryDTO;
import com.agri.backend.entity.Category;
import com.agri.backend.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类接口
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 按类型查询分类列表
     */
    @GetMapping("/list")
    public Result<List<Category>> list(@RequestParam String type) {
        return Result.success(categoryService.getListByType(type));
    }

    /**
     * 新增分类
     */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setSort(dto.getSort());
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        categoryService.save(category);
        return Result.success();
    }
}
