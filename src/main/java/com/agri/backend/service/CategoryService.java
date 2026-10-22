package com.agri.backend.service;

import com.agri.backend.entity.Category;
import com.baomidou.mybatisplus.spring.service.IService;

import java.util.List;

/**
 * 分类 Service
 */
public interface CategoryService extends IService<Category> {

    /**
     * 按类型查询分类列表，按 sort 升序
     */
    List<Category> getListByType(String type);
}
