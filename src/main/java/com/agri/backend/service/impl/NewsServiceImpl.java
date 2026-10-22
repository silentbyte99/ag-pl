package com.agri.backend.service.impl;

import com.agri.backend.common.BusinessException;
import com.agri.backend.dto.NewsDTO;
import com.agri.backend.entity.News;
import com.agri.backend.mapper.CategoryMapper;
import com.agri.backend.mapper.NewsMapper;
import com.agri.backend.service.NewsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 资讯 Service 实现
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public IPage<News> getNewsPage(Integer page, Integer pageSize, Long categoryId, String keyword) {
        return lambdaQuery()
                .eq(News::getStatus, 1)
                .eq(categoryId != null, News::getCategoryId, categoryId)
                .like(keyword != null && !keyword.trim().isEmpty(), News::getTitle, keyword)
                .orderByDesc(News::getIsTop)
                .orderByDesc(News::getCreatedAt)
                .page(new Page<>(page, pageSize));
    }

    @Override
    public void addNews(NewsDTO dto, Long authorId) {
        // 校验分类是否存在
        if (categoryMapper.selectById(dto.getCategoryId()) == null) {
            throw new BusinessException("分类不存在");
        }

        News news = new News();
        BeanUtils.copyProperties(dto, news);
        news.setAuthorId(authorId);
        news.setViewCount(0);
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());
        save(news);
    }
}
