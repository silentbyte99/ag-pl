package com.agri.backend.controller;

import com.agri.backend.common.Result;
import com.agri.backend.dto.CommentDTO;
import com.agri.backend.dto.ReportDTO;
import com.agri.backend.entity.Comment;
import com.agri.backend.service.CommentService;
import com.agri.backend.service.FavoriteService;
import com.agri.backend.service.LikeService;
import com.agri.backend.service.ReportService;
import com.agri.backend.utils.SensitiveWordUtils;
import com.agri.backend.utils.UserContext;
import com.agri.backend.utils.XssUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 交互接口（评论、点赞、收藏、举报）
 */
@RestController
@RequestMapping("/interaction")
public class InteractionController {

    @Resource
    private CommentService commentService;

    @Resource
    private LikeService likeService;

    @Resource
    private FavoriteService favoriteService;

    @Resource
    private ReportService reportService;

    /**
     * 发表评论（需登录）
     */
    @PostMapping("/comment/add")
    public Result<Void> addComment(@RequestBody CommentDTO dto) {
        dto.setContent(XssUtils.clean(SensitiveWordUtils.filter(dto.getContent())));
        Long userId = UserContext.getUserId();
        commentService.addComment(dto, userId);
        return Result.success();
    }

    /**
     * 删除评论（需登录，只能删自己的）
     */
    @DeleteMapping("/comment/{id}")
    public Result<Void> deleteComment(@PathVariable Long id) {
        Long userId = UserContext.getUserId();
        commentService.deleteComment(id, userId);
        return Result.success();
    }

    /**
     * 查询评论列表（公开）
     */
    @GetMapping("/comment/list")
    public Result<List<Comment>> list(@RequestParam Long targetId, @RequestParam String targetType) {
        return Result.success(commentService.getComments(targetId, targetType));
    }

    /**
     * 点赞/取消点赞（需登录）
     */
    @PostMapping("/like/toggle")
    public Result<Boolean> toggleLike(@RequestParam Long targetId, @RequestParam String targetType) {
        Long userId = UserContext.getUserId();
        return Result.success(likeService.toggleLike(targetId, targetType, userId));
    }

    /**
     * 收藏/取消收藏（需登录）
     */
    @PostMapping("/favorite/toggle")
    public Result<Boolean> toggleFavorite(@RequestParam Long targetId, @RequestParam String targetType) {
        Long userId = UserContext.getUserId();
        return Result.success(favoriteService.toggleFavorite(targetId, targetType, userId));
    }

    /**
     * 举报（需登录）
     */
    @PostMapping("/report/add")
    public Result<Void> addReport(@RequestBody ReportDTO dto) {
        Long userId = UserContext.getUserId();
        reportService.addReport(dto, userId);
        return Result.success();
    }
}
