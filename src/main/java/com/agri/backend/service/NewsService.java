package com.agri.backend.service;

import com.agri.backend.dto.NewsDTO;
import com.agri.backend.entity.News;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * 资讯 Service
 */
public interface NewsService extends IService<News> {

    /**
     * 分页查询已上架资讯
     */
    IPage<News> getNewsPage(Integer page, Integer pageSize, Long categoryId, String keyword);

    /**
     * 新增资讯
     */
    void addNews(NewsDTO dto, Long authorId);
}
