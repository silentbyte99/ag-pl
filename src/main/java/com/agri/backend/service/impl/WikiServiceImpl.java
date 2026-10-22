package com.agri.backend.service.impl;

import com.agri.backend.common.BusinessException;
import com.agri.backend.dto.WikiDTO;
import com.agri.backend.entity.Wiki;
import com.agri.backend.mapper.CategoryMapper;
import com.agri.backend.mapper.WikiMapper;
import com.agri.backend.service.WikiService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 百科 Service 实现
 */
@Service
public class WikiServiceImpl extends ServiceImpl<WikiMapper, Wiki> implements WikiService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public IPage<Wiki> getWikiPage(Integer page, Integer pageSize, Long categoryId, String keyword) {
        return lambdaQuery()
                .eq(Wiki::getStatus, 1)
                .eq(categoryId != null, Wiki::getCategoryId, categoryId)
                .like(keyword != null && !keyword.trim().isEmpty(), Wiki::getName, keyword)
                .orderByDesc(Wiki::getCreatedAt)
                .page(new Page<>(page, pageSize));
    }

    @Override
    public void addWiki(WikiDTO dto) {
        if (categoryMapper.selectById(dto.getCategoryId()) == null) {
            throw new BusinessException("分类不存在");
        }
        Wiki wiki = new Wiki();
        BeanUtils.copyProperties(dto, wiki);
        wiki.setViewCount(0);
        wiki.setCreatedAt(LocalDateTime.now());
        wiki.setUpdatedAt(LocalDateTime.now());
        save(wiki);
    }
}
