package com.agri.backend.controller;

import com.agri.backend.common.Result;
import com.agri.backend.entity.News;
import com.agri.backend.entity.Tech;
import com.agri.backend.entity.Wiki;
import com.agri.backend.service.NewsService;
import com.agri.backend.service.TechService;
import com.agri.backend.service.WikiService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页聚合接口
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private NewsService newsService;

    @Resource
    private TechService techService;

    @Resource
    private WikiService wikiService;

    /**
     * 首页数据：最新 5 条资讯、技术、百科
     */
    @GetMapping
    public Result<Map<String, Object>> home() {
        List<News> newsList = newsService.lambdaQuery()
                .eq(News::getStatus, 1)
                .orderByDesc(News::getCreatedAt)
                .last("limit 5")
                .list();

        List<Tech> techList = techService.lambdaQuery()
                .eq(Tech::getStatus, 1)
                .orderByDesc(Tech::getCreatedAt)
                .last("limit 5")
                .list();

        List<Wiki> wikiList = wikiService.lambdaQuery()
                .eq(Wiki::getStatus, 1)
                .orderByDesc(Wiki::getCreatedAt)
                .last("limit 5")
                .list();

        Map<String, Object> data = new HashMap<>();
        data.put("newsList", newsList);
        data.put("techList", techList);
        data.put("wikiList", wikiList);
        return Result.success(data);
    }
}
