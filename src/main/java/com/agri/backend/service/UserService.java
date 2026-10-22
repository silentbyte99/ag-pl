package com.agri.backend.service;

import com.agri.backend.dto.LoginDTO;
import com.agri.backend.dto.RegisterDTO;
import com.agri.backend.dto.UserUpdateDTO;
import com.agri.backend.entity.User;
import com.agri.backend.vo.LoginVO;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * 用户 Service
 */
public interface UserService extends IService<User> {

    /**
     * 注册，成功后直接返回登录信息
     */
    LoginVO register(RegisterDTO dto);

    /**
     * 登录
     */
    LoginVO login(LoginDTO dto);

    /**
     * 更新当前用户信息（昵称/头像/手机号/邮箱/地区/简介）
     */
    void updateUser(UserUpdateDTO dto, Long userId);
}
