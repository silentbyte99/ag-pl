package com.agri.backend.service.impl;

import com.agri.backend.common.BusinessException;
import com.agri.backend.dto.TechDTO;
import com.agri.backend.entity.Tech;
import com.agri.backend.mapper.CategoryMapper;
import com.agri.backend.mapper.TechMapper;
import com.agri.backend.service.TechService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 技术 Service 实现
 */
@Service
public class TechServiceImpl extends ServiceImpl<TechMapper, Tech> implements TechService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public IPage<Tech> getTechPage(Integer page, Integer pageSize, Long categoryId, String keyword) {
        return lambdaQuery()
                .eq(Tech::getStatus, 1)
                .eq(categoryId != null, Tech::getCategoryId, categoryId)
                .like(keyword != null && !keyword.trim().isEmpty(), Tech::getTitle, keyword)
                .orderByDesc(Tech::getCreatedAt)
                .page(new Page<>(page, pageSize));
    }

    @Override
    public void addTech(TechDTO dto, Long authorId) {
        if (categoryMapper.selectById(dto.getCategoryId()) == null) {
            throw new BusinessException("分类不存在");
        }
        Tech tech = new Tech();
        BeanUtils.copyProperties(dto, tech);
        tech.setAuthorId(authorId);
        tech.setViewCount(0);
        tech.setCreatedAt(LocalDateTime.now());
        tech.setUpdatedAt(LocalDateTime.now());
        save(tech);
    }
}
