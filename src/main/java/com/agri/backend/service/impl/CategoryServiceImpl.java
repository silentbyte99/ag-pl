package com.agri.backend.service.impl;

import com.agri.backend.entity.Category;
import com.agri.backend.mapper.CategoryMapper;
import com.agri.backend.service.CategoryService;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类 Service 实现
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> getListByType(String type) {
        return lambdaQuery()
                .eq(Category::getType, type)
                .orderByAsc(Category::getSort)
                .list();
    }
}
