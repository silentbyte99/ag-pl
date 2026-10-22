package com.agri.backend.service;

import com.agri.backend.dto.WikiDTO;
import com.agri.backend.entity.Wiki;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * 百科 Service
 */
public interface WikiService extends IService<Wiki> {

    /**
     * 分页查询已上架百科
     */
    IPage<Wiki> getWikiPage(Integer page, Integer pageSize, Long categoryId, String keyword);

    /**
     * 新增百科
     */
    void addWiki(WikiDTO dto);
}
