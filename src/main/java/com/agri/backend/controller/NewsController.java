package com.agri.backend.controller;

import com.agri.backend.common.Result;
import com.agri.backend.dto.NewsDTO;
import com.agri.backend.entity.News;
import com.agri.backend.service.NewsService;
import com.agri.backend.utils.UserContext;
import com.agri.backend.utils.ViewCountUtils;
import com.agri.backend.utils.XssUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资讯接口
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    @Resource
    private ViewCountUtils viewCountUtils;

    /**
     * 分页查询资讯
     */
    @GetMapping("/page")
    public Result<IPage<News>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Long categoryId,
            String keyword) {
        return Result.success(newsService.getNewsPage(page, pageSize, categoryId, keyword));
    }

    /**
     * 新增资讯（需登录）
     */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody NewsDTO dto) {
        Long authorId = UserContext.getUserId();
        newsService.addNews(dto, authorId);
        return Result.success();
    }

    /**
     * 资讯详情
     */
    @GetMapping("/{id}")
    public Result<News> detail(@PathVariable Long id, HttpServletRequest request) {
        Long userId = UserContext.getUserId();
        String identifier = userId != null ? String.valueOf(userId) : request.getRemoteAddr();
        viewCountUtils.checkAndAddView("news", id, identifier);

        News news = newsService.getById(id);
        if (news != null) {
            news.setTitle(XssUtils.clean(news.getTitle()));
            news.setContent(XssUtils.clean(news.getContent()));
        }
        return Result.success(news);
    }

    /**
     * 获取资讯浏览量（来自 Redis）
     */
    @GetMapping("/{id}/views")
    public Result<Object> views(@PathVariable Long id) {
        return Result.success(viewCountUtils.getViewCount("news", id));
    }
}
