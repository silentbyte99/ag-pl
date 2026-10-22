package com.agri.backend.controller;

import com.agri.backend.common.BusinessException;
import com.agri.backend.common.Result;
import com.agri.backend.dto.LoginDTO;
import com.agri.backend.dto.RegisterDTO;
import com.agri.backend.dto.UserUpdateDTO;
import com.agri.backend.entity.User;
import com.agri.backend.service.UserService;
import com.agri.backend.utils.UserContext;
import com.agri.backend.vo.LoginVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<LoginVO> register(@RequestBody RegisterDTO dto) {
        return Result.success(userService.register(dto));
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO dto) {
        return Result.success(userService.login(dto));
    }

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/info")
    public Result<User> info() {
        Long userId = UserContext.getUserId();
        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        // 安全处理：不把密码返回给前端
        user.setPassword(null);
        return Result.success(user);
    }

    /**
     * 更新当前用户信息（需登录）
     */
    @PostMapping("/update")
    @PutMapping("/update")
    public Result<User> update(@RequestBody UserUpdateDTO dto) {
        Long userId = UserContext.getUserId();
        userService.updateUser(dto, userId);
        User user = userService.getById(userId);
        user.setPassword(null);
        return Result.success(user);
    }
}
