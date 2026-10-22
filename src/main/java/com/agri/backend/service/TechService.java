package com.agri.backend.service;

import com.agri.backend.dto.TechDTO;
import com.agri.backend.entity.Tech;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * 技术 Service
 */
public interface TechService extends IService<Tech> {

    /**
     * 分页查询已上架技术
     */
    IPage<Tech> getTechPage(Integer page, Integer pageSize, Long categoryId, String keyword);

    /**
     * 新增技术
     */
    void addTech(TechDTO dto, Long authorId);
}
